package pojos.map;

import pojos.Pojo;

import java.util.ArrayList;

public class AddPlaceReq implements Pojo {
    public Location location;
    public int accuracy;
    public String name;
    public String phone_number;
    public String address;
    public ArrayList<String> types;
    public String website;
    public String language;

    public class Location{
        public double lat;
        public double lng;
    }
}
