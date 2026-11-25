package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Actor;
import model.Order;
import adapters.DynamoDBOrderRepository;
import config.DynamoDBLocalTestClient;
import ports.OrderRepository;
import questions.PersistOrderQuestion;
import tasks.PersistOrderTask;
import tasks.RetrieveOrderTask;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions de los escenarios de persistencia y lectura de pedidos.
 * Cada paso delega en una Task o Question de Screenplay: los pasos no
 * contienen logica de acceso a DynamoDB.
 */
public class XSteps {

    private static final String TABLE_NAME = "orders";

    private Actor actor;
    private Order order;
    private String orderId;
    private RetrieveOrderTask retrieveTask;

    @Given("the actor {word} wants to persist an order")
    public void the_actor_wants_to_persist_an_order(String actorName) {
        actor = Actor.named(actorName);
    }

    @Given("the order has the following details:")
    public void the_order_has_the_following_details(Map<String, String> orderDetails) {
        // Firma real de Order: (String, String, List<OrderItem>, OrderStatus, String, String).
        // Los pedidos de prueba arrancan sin items y con el primer estado del enum.
        orderId = orderDetails.get("orderId");
        order = new Order(
            orderId,
            orderDetails.get("customerId"),
            List.of(),
            Order.OrderStatus.values()[0],
            orderDetails.getOrDefault("customerName", ""),
            orderDetails.getOrDefault("currency", "")
        );
    }

    @When("the actor persists the order")
    public void the_actor_persists_the_order() {
        OrderRepository repository = repository();
        actor.attemptsTo(new PersistOrderTask(order, repository));
    }

    @Then("the order should be persisted successfully")
    public void the_order_should_be_persisted_successfully() {
        Boolean persisted = new PersistOrderQuestion(orderId, repository()).answeredBy(actor);
        assertThat(persisted).isTrue();
    }

    @When("the actor retrieves the order by id {word}")
    public void the_actor_retrieves_the_order_by_id(String idToRetrieve) {
        retrieveTask = new RetrieveOrderTask(idToRetrieve, repository());
        actor.attemptsTo(retrieveTask);
    }

    @Then("the retrieved order should match the original order")
    public void the_retrieved_order_should_match_the_original_order() {
        assertThat(retrieveTask).isNotNull();
        assertThat(retrieveTask.getRetrievedOrder()).isEqualTo(order);
    }

    private OrderRepository repository() {
        return new DynamoDBOrderRepository(
            DynamoDBLocalTestClient.getClient().getDynamoDbClient(),
            TABLE_NAME
        );
    }
}