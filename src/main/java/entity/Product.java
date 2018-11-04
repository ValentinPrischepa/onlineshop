package entity;

import java.sql.Timestamp;

public class Product {
    private int id;
    private String name;
    private String price;
    private String image;
    private Timestamp date;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Product() {
    }

    public Product(String name, String price, String image, Timestamp date) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public Timestamp getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                '}';
    }
}
