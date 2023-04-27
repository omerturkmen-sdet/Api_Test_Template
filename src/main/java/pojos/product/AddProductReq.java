package pojos.product;

import pojos.Pojo;

import java.util.ArrayList;

public class AddProductReq implements Pojo {
    public int id;
    public String title;
    public String description;
    public int price;
    public double discountPercentage;
    public double rating;
    public int stock;
    public String brand;
    public String category;
    public String thumbnail;
    public ArrayList<String> images;
}
