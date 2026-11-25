// TODO(reto): archivo stubbeado; implementa los cuerpos.
package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import ports.OrderRepository;

import java.time.Duration;

public class PersistOrderQuestion implements Question<Boolean> {

    private final String orderId;
    private final OrderRepository orderRepository;
    private final int maxRetries;
    private final Duration retryInterval;

    public PersistOrderQuestion(String orderId, OrderRepository orderRepository) {
        this.orderId = orderId;
        this.orderRepository = orderRepository;
        this.maxRetries = 5;
        this.retryInterval = Duration.ofMillis(500);
    }

    public static PersistOrderQuestionBuilder hasOrderPersisted(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): hasOrderPersisted");
    }

    @Override
    public Boolean answeredBy(Actor actor) {
      throw new UnsupportedOperationException("TODO(reto): answeredBy");
    }

    public static class PersistOrderQuestionBuilder {
        private final String orderId;
        private OrderRepository orderRepository;
        private int maxRetries = 5;
        private Duration retryInterval = Duration.ofMillis(500);

        public PersistOrderQuestionBuilder(String orderId) {
            this.orderId = orderId;
        }

        public PersistOrderQuestionBuilder usingRepository(OrderRepository repository) {
            this.orderRepository = repository;
            return this;
        }

        public PersistOrderQuestionBuilder withMaxRetries(int retries) {
            this.maxRetries = retries;
            return this;
        }

        public PersistOrderQuestionBuilder withRetryInterval(Duration interval) {
            this.retryInterval = interval;
            return this;
        }

        public PersistOrderQuestion build() {
            if (orderRepository == null) {
                throw new IllegalStateException("OrderRepository no puede ser null");
            }
            return new PersistOrderQuestion(orderId, orderRepository);
        }
    }

    public static class OrderQueryException extends RuntimeException {
        public OrderQueryException(String message) {
            super(message);
        }

        public OrderQueryException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}