// TODO(reto): archivo stubbeado; implementa los cuerpos.
package config;

import model.Order;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClientBuilder;

import java.net.URI;
import java.util.Map;

public class DynamoDBLocalTestClient {

    private static final String LOCALSTACK_ENDPOINT = System.getenv().getOrDefault("DYNAMODB_ENDPOINT", "http://localhost:4566");
    private static final String REGION = System.getenv().getOrDefault("AWS_REGION", "us-east-1");

    private final DynamoDbClient client;

    public DynamoDBLocalTestClient() {
        this.client = createLocalClient();
    }

    private DynamoDbClient createLocalClient() {
      throw new UnsupportedOperationException("TODO(reto): createLocalClient");
    }

    public static DynamoDBLocalTestClient getClient() {
      throw new UnsupportedOperationException("TODO(reto): getClient");
    }

    public void saveOrder(Order order) {
      throw new UnsupportedOperationException("TODO(reto): saveOrder");
    }

    public Order getOrder(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): getOrder");
    }

    public void deleteOrder(String orderId) {
      throw new UnsupportedOperationException("TODO(reto): deleteOrder");
    }

    public DynamoDbClient getDynamoDbClient() {
        return client;
    }

    public static class DynamoDBLocalNotAvailableException extends RuntimeException {
        public DynamoDBLocalNotAvailableException(String message) {
            super(message);
        }

        public DynamoDBLocalNotAvailableException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}