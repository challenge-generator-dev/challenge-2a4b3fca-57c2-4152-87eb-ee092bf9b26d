// TODO(reto): archivo stubbeado; implementa los cuerpos.
package tasks;

import model.Order;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Actor;
import ports.OrderRepository;

public class PersistOrderTask implements Task {

    private final Order order;
    private final OrderRepository orderRepository;

    public PersistOrderTask(Order order, OrderRepository orderRepository) {
        this.order = order;
        this.orderRepository = orderRepository;
    }

    public static PersistOrderTaskBuilder withOrder(Order order) {
      throw new UnsupportedOperationException("TODO(reto): withOrder");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
      throw new UnsupportedOperationException("TODO(reto): performAs");
    }

    public static class PersistOrderTaskBuilder {
        private final Order order;
        private OrderRepository orderRepository;

        public PersistOrderTaskBuilder(Order order) {
            this.order = order;
        }

        public PersistOrderTaskBuilder usingRepository(OrderRepository repository) {
            this.orderRepository = repository;
            return this;
        }

        public PersistOrderTask build() {
            if (orderRepository == null) {
                throw new IllegalStateException("OrderRepository no puede ser null");
            }
            return new PersistOrderTask(order, orderRepository);
        }
    }

    public static class OrderPersistenceException extends RuntimeException {
        public OrderPersistenceException(String message) {
            super(message);
        }

        public OrderPersistenceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}