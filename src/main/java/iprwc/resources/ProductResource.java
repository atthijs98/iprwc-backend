package iprwc.resources;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.dropwizard.auth.Auth;
import iprwc.api.Product;
import iprwc.api.ProductDirector;
import iprwc.api.User;
import iprwc.core.Body;
import iprwc.service.ProductService;
import iprwc.util.MessageFactory.Language;
import iprwc.util.MessageFactory.MessageFactory;
import iprwc.util.MessageFactory.MessageUtil;
import org.jdbi.v3.core.Jdbi;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static iprwc.util.MessageFactory.Message.*;

//@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/products")
public class ProductResource {
    private Jdbi dao;
    private ProductService productService;
    private MessageFactory messageFactory;
    MessageUtil english;
    MessageUtil dutch;

    public ProductResource() {
        productService = new ProductService();
        messageFactory = new MessageFactory();

        english = messageFactory.getMessageUtil(Language.english);
        dutch = messageFactory.getMessageUtil(Language.dutch);
    }

    @GET
    public Response getProducts() {
        return Body.buildResponse(
          Response.Status.OK,
                english.get(ALL_PRODUCTS_RETRIEVED) + "<br>" +
                        dutch.get(ALL_PRODUCTS_RETRIEVED_FAILED),
                productService.getProducts()
        );
    }

    @Path("{id}")
    @GET
    public Response getProductFromId(@Auth User authUser, @PathParam("id") int id) {
        try {
            return productService.getProductFromId(authUser, id);
        } catch (NotAuthorizedException e) {
            return e.getResponse();
        }
    }

    @Path("{id}")
    @DELETE
    public Response deleteProduct(@Auth User user, @PathParam("id") int id) {
        try {
            productService.deleteProduct(user, id);
            return Body.buildResponse(
              Response.Status.OK,
              messageFactory.getMessageUtil(user.getLanguage()).get(PRODUCT_DELETED),
              id
            );
        } catch (NotAuthorizedException e) {
            return e.getResponse();
        }
    }

    @Path("add")
    @POST
    public Response addProduct(@Auth User authUser, String productJson) {
//        System.out.println(productJson);

        JsonObject jsonObj = new Gson().fromJson(productJson, JsonObject.class);
        JsonElement productImagesJson = jsonObj.get("images");
        JsonElement productDirectorsJson = jsonObj.get("directors");
        jsonObj.remove("directors");
        jsonObj.remove("images");
        System.out.println(jsonObj);
        Product product = new Gson().fromJson(jsonObj.toString(), Product.class);



        try {
            return Body.buildResponse(
                    Response.Status.OK,
                    messageFactory.getMessageUtil(authUser.getLanguage()).get(PRODUCT_ADDED),
                    productService.addProduct(product, authUser)
            );

        } catch (NotAuthorizedException e) {
            return e.getResponse();
        }

    }
}
