package ports;

import model.Order;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(String orderId);
    void delete(String orderId);
}