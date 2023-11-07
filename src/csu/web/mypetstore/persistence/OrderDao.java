package csu.web.mypetstore.persistence;

import java.util.List;
import csu.web.mypetstore.domain.Order;

public interface OrderDao {
    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    void insertOrder(Order order);

    void insertOrderStatus(Order order);

}
