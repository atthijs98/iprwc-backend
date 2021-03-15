package iprwc.db;

import iprwc.api.Order;
import iprwc.db.mappers.ProductMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

//@RegisterRowMapper(ProductMapper.class)
public interface OrderDAO {
    @SqlQuery("SELECT * FROM order")
    List<Order> getOrders();

    @SqlQuery("SELECT * FROM order WHERE id = :id")
    Order getOrderFromId(@Bind("id") int id);

    @SqlQuery("SELECT * FROM order WHERE person_id = :user_id")
    List<Order> getOrdersByUserId(@Bind("user_id") int user_id);
}
