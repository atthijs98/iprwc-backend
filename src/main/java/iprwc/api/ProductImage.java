package iprwc.api;

public class ProductImage {
    private int id;
    private int productId;
    private String path;
    private String description;

    public ProductImage(int id, int productId, String path, String description) {
        this.id = id;
        this.productId = productId;
        this.path = path;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
