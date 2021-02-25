package iprwc.api;

public class OrderProduct {

    private int userId;
    private int productId;

    public OrderProduct(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
