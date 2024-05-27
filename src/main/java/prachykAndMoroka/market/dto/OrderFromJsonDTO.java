package prachykAndMoroka.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import prachykAndMoroka.market.model.OrderStatus;

import java.util.Objects;

public class OrderFromJsonDTO {
    @JsonProperty("order_status")
    private OrderStatus orderStatus;
    @JsonProperty("user_id")
    private Long userId;

    public OrderFromJsonDTO() {
    }

    public OrderFromJsonDTO(OrderStatus orderStatus, Long userId) {
        this.orderStatus = orderStatus;
        this.userId = userId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderFromJsonDTO{" +
                "orderStatus=" + orderStatus +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderFromJsonDTO)) return false;
        OrderFromJsonDTO that = (OrderFromJsonDTO) o;
        return orderStatus == that.orderStatus && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatus, userId);
    }
}
