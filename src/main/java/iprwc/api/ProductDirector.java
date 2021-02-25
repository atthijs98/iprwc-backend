package iprwc.api;

public class ProductDirector {
    private int id;
    private int productId;
    private String name;

    public ProductDirector(int id, String name, int productId) {
        this.id = id;
        this.name = name;
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
