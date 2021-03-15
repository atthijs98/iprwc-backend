package iprwc.db;

import iprwc.api.Product;
import iprwc.api.ProductImage;
import iprwc.db.mappers.ProductDirectorMapper;
import iprwc.db.mappers.ProductImageMapper;
import iprwc.db.mappers.ProductMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

//@RegisterRowMapper(ProductImageMapper.class)
//@RegisterRowMapper(ProductDirectorMapper.class)
//@RegisterRowMapper(ProductMapper.class)
public interface ProductDAO {
    @SqlQuery("SELECT * FROM product")
    List<Product> getProducts();

    @SqlQuery("SELECT * FROM product WHERE id = :id")
    Product getProductFromId(@Bind("id") int id);

    @SqlUpdate("DELETE FROM product WHERE id = :id")
    public void deleteProduct(@Bind("id") int id);

    @SqlQuery("SELECT en_title FROM product WHERE en_title = :name AND year = :year")
    String checkForDuplicate(@Bind("name") String name, @Bind("year") String year);

    @SqlQuery("INSERT INTO product(en_title, or_title, rom_or_title, year, runtime, plot, poster, trailer, price) values (:enTitle, :orTitle, :romOrTitle, :year, :runtime, :plot, :poster, :trailer, :price)")
    Product addProduct(@Bind("enTitle") String enTitle,
                       @Bind("orTitle") String OrTitle,
                       @Bind("romOrTitle") String romOrTitle,
                       @Bind("year") String year,
                       @Bind("runtime") String runtime,
                       @Bind("plot") String plot,
                       @Bind("poster") String poster,
                       @Bind("trailer") String trailer,
                       @Bind("price") double price);

}
