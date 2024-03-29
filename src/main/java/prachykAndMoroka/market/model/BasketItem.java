package prachykAndMoroka.market.model;

public class BasketItem {
//    @SerializedName("product_id")
    private Long productId;
//    @SerializedName("quantity")
    private int quantity;

    public BasketItem(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Items{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
