package iprwc.api;

public class Order {
    private int id;
    private User user;
    private String street;
    private String number;
    private String postalCode;
    private String country;
    private String city;
    private String name;
    private String email;
    private Double total;

    public Order(int id, User user, String street, String number, String postalCode, String country,
                 String city, String name, String email, Double total) {
        this.id = id;
        this.user = user;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.country = country;
        this.city = city;
        this.name = name;
        this.email = email;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotal() {
        return total;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreet() {
        return street;
    }
}
