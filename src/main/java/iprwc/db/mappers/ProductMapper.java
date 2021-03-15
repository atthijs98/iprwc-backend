package iprwc.db.mappers;

import iprwc.api.Product;
import iprwc.api.ProductDirector;
import iprwc.api.ProductImage;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Product(rs.getInt("id"),
                rs.getString("en_title"),
                rs.getString("or_title"),
                rs.getString("rom_or_title"),
                rs.getString("year"),
                rs.getString("runtime"),
                rs.getString("plot"),
                rs.getString("poster"),
                rs.getString("trailer"),
                rs.getDouble("price"),
                rs.getTimestamp("created_at")
        );
    }
}
