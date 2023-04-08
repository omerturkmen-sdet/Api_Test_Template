package pojos.product;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class AddProductResponse {

    private int id;
    private String title;
    private int price;
    private int stock;
    private double rating;
    private ArrayList<String> images;
    private String thumbnail;
    private String description;
    private String brand;
    private String category;

}
