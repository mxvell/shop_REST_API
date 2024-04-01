package prachykAndMoroka.market.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ProductFromJsonDTO {
    @JsonProperty("product_id")
    private long productId;
    @JsonProperty("quantity")
    private int quantity;

    public ProductFromJsonDTO() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductFromJsonDTO{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFromJsonDTO)) return false;
        ProductFromJsonDTO that = (ProductFromJsonDTO) o;
        return productId == that.productId && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }
}
