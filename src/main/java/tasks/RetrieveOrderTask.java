// TODO(reto): archivo stubbeado; implementa los cuerpos.
package tasks;

import model.Order;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import ports.OrderRepository;

public class RetrieveOrderTask implements Task {

    private final String orderId;
    private final OrderRepository orderRepository;
    private Order retrievedOrder;

    public RetrieveOrderTask(String orderId, OrderRepository orderRepository) {
        this.orderId = orderId;
        this.orderRepository = orderRepository;
    }

    public static RetrieveOrderTaskBuilder withOrderId(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): withOrderId");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
      throw new UnsupportedOperationException("TODO(reto): performAs");
    }

    public Order getRetrievedOrder() {
        return retrievedOrder;
    }

    public static class RetrieveOrderTaskBuilder {
        private final String orderId;
        private OrderRepository orderRepository;

        public RetrieveOrderTaskBuilder(String orderId) {
            this.orderId = orderId;
        }

        public RetrieveOrderTaskBuilder usingRepository(OrderRepository repository) {
            this.orderRepository = repository;
            return this;
        }

        public RetrieveOrderTask build() {
            if (orderRepository == null) {
                throw new IllegalStateException("OrderRepository no puede ser null");
            }
            return new RetrieveOrderTask(orderId, orderRepository);
        }
    }

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderRetrievalException extends RuntimeException {
        public OrderRetrievalException(String message) {
            super(message);
        }

        public OrderRetrievalException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}