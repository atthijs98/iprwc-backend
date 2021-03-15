package iprwc.api;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String enTitle;
    private String orTitle;
    private String romOrTitle;
    private String year;
    private String runtime;
    private String plot;
    private String poster;
    private String trailer;
    private Double price;
    private Timestamp createdAt;

    public Product(int id, String enTitle, String orTitle, String romOrTitle,
                   String year, String runtime, String plot, String poster, String trailer, double price, Timestamp createdAt) {
        this.id = id;
        this.enTitle = enTitle;
        this.orTitle = orTitle;
        this.romOrTitle = romOrTitle;
        this.year = year;
        this.runtime = runtime;
        this.plot = plot;
        this.poster = poster;
        this.trailer = trailer;
        this.price = price;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public String getOrTitle() {
        return orTitle;
    }

    public String getRomOrTitle() {
        return romOrTitle;
    }

    public Double getPrice() {
        return price;
    }

    public String getPlot() {
        return plot;
    }

    public String getPoster() {
        return poster;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getYear() {
        return year;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public void setOrTitle(String orTitle) {
        this.orTitle = orTitle;
    }

    public void setRomOrTitle(String romOrTitle) {
        this.romOrTitle = romOrTitle;
    }
}
