package model;

import java.util.List;
import java.util.Objects;

public record Order(
    String orderId,
    String customerName,
    List<OrderItem> items,
    OrderStatus status,
    String createdAt,
    String updatedAt) {

    public Order {
        Objects.requireNonNull(orderId, "Order ID cannot be null");
        Objects.requireNonNull(customerName, "Customer name cannot be null");
        Objects.requireNonNull(items, "Items list cannot be null");
        Objects.requireNonNull(status, "Order status cannot be null");
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
    }

    public enum OrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }

    public static class OrderItem {
        private final String productId;
        private final String productName;
        private final int quantity;
        private final double price;

        public OrderItem(String productId, String productName, int quantity, double price) {
            this.productId = Objects.requireNonNull(productId, "Product ID cannot be null");
            this.productName = Objects.requireNonNull(productName, "Product name cannot be null");
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            if (price <= 0) {
                throw new IllegalArgumentException("Price must be positive");
            }
            this.quantity = quantity;
            this.price = price;
        }

        public String getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price * quantity;
        }
    }
}