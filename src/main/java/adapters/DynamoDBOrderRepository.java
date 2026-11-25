package adapters;

import model.Order;
import ports.OrderRepository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DynamoDBOrderRepository implements OrderRepository {
    private final DynamoDbClient dynamoDbClient;
    private final String tableName;

    public DynamoDBOrderRepository(DynamoDbClient dynamoDbClient, String tableName) {
        this.dynamoDbClient = dynamoDbClient;
        this.tableName = tableName;
    }

    @Override
    public void save(Order order) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("orderId", AttributeValue.builder().s(order.orderId()).build());
        item.put("customerName", AttributeValue.builder().s(order.customerName()).build());
        item.put("status", AttributeValue.builder().s(order.status().name()).build());
        item.put("createdAt", AttributeValue.builder().s(order.createdAt()).build());
        item.put("updatedAt", AttributeValue.builder().s(order.updatedAt()).build());

        String itemsList = order.items().stream()
                .map(itemObj -> String.format("%s|%s|%d|%.2f",
                        itemObj.getProductId(),
                        itemObj.getProductName(),
                        itemObj.getQuantity(),
                        itemObj.getPrice()))
                .collect(Collectors.joining(","));

        item.put("items", AttributeValue.builder().s(itemsList).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        try {
            dynamoDbClient.putItem(request);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Failed to save order to DynamoDB", e);
        }
    }

    @Override
    public Optional<Order> findById(String orderId) {
        GetItemRequest request = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        try {
            GetItemResponse response = dynamoDbClient.getItem(request);
            if (!response.hasItem()) {
                return Optional.empty();
            }

            Map<String, AttributeValue> item = response.item();
            String customerName = item.get("customerName").s();
            Order.OrderStatus status = Order.OrderStatus.valueOf(item.get("status").s());
            String createdAt = item.get("createdAt").s();
            String updatedAt = item.get("updatedAt").s();

            String[] itemsArray = item.get("items").s().split(",");
            var items = java.util.Arrays.stream(itemsArray)
                    .map(itemStr -> {
                        String[] parts = itemStr.split("\\|");
                        return new Order.OrderItem(
                                parts[0],
                                parts[1],
                                Integer.parseInt(parts[2]),
                                Double.parseDouble(parts[3])
                        );
                    })
                    .collect(Collectors.toList());

            Order order = new Order(orderId, customerName, items, status, createdAt, updatedAt);
            return Optional.of(order);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Failed to retrieve order from DynamoDB", e);
        }
    }

    @Override
    public void delete(String orderId) {
        DeleteItemRequest request = DeleteItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("orderId", AttributeValue.builder().s(orderId).build()))
                .build();

        try {
            dynamoDbClient.deleteItem(request);
        } catch (DynamoDbException e) {
            throw new RuntimeException("Failed to delete order from DynamoDB", e);
        }
    }
}