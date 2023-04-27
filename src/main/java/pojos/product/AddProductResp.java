package pojos.product;

import pojos.Pojo;

import java.util.ArrayList;

public class AddProductResp implements Pojo {
    public int id;
    public String title;
    public int price;
    public int stock;
    public double rating;
    public ArrayList<String> images;
    public String thumbnail;
    public String description;
    public String brand;
    public String category;
}
