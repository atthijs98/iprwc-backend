package iprwc.service;

import iprwc.api.Product;
import iprwc.api.User;
import iprwc.core.Body;
import iprwc.db.ProductDAO;
import iprwc.iprwcApplication;
import iprwc.util.MessageFactory.Language;
import iprwc.util.MessageFactory.Message;
import iprwc.util.MessageFactory.MessageFactory;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.util.List;

import static iprwc.util.MessageFactory.Message.*;

public class ProductService {
    private ProductDAO productDAO;
    private MessageFactory messageFactory;

    public ProductService() {
        productDAO = iprwcApplication.jdbiConnection.onDemand(ProductDAO.class);
        messageFactory = new MessageFactory();
    }

    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    public Response getProductFromId(User authUser, int id) {

        Product product = productDAO.getProductFromId(id);

        return Body.buildResponse(
                Response.Status.OK,
                messageFactory.getMessageUtil(authUser.getLanguage()).get(PRODUCT_RETRIEVED),
                product
        );
    }

    public void deleteProduct(User authUser, int id) {
        productDAO.deleteProduct(id);
    }

    public Object addProduct(Product product, User authUser) throws BadRequestException {
        String title = productDAO.checkForDuplicate(product.getEnTitle(), product.getYear());

        if (title != null) {
            throw new BadRequestException(
                    Body.buildResponse(
                            Response.Status.BAD_REQUEST,
                            messageFactory.getMessageUtil(authUser.getLanguage()).get(DUPLICATE_PRODUCT),
                            null
                    )
            );
        }

        return Body.buildResponse(
                Response.Status.OK,
                messageFactory.getMessageUtil(authUser.getLanguage()).get(PRODUCT_ADDED),
                productDAO.addProduct(product.getEnTitle(),
                        product.getOrTitle(),
                        product.getRomOrTitle(),
                        product.getYear(),
                        product.getRuntime(),
                        product.getPlot(),
                        product.getPoster(),
                        product.getTrailer(),
                        product.getPrice())
                );

    }

}
