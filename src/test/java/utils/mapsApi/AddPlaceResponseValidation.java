package utils.mapsApi;

import pojos.map.AddPlaceResp;
import utils.responseValidation.GsonReader;
import utils.responseValidation.IResponseValidatable;

public class AddPlaceResponseValidation implements IResponseValidatable {

    private AddPlaceResp response = (AddPlaceResp) new GsonReader(new AddPlaceResp()).getResponse();
    @Override
    public void responseValidation() {
        System.out.println("response.place_id = " + response.place_id);
        System.out.println("response.id = " + response.id);
        System.out.println("response.status = " + response.status);
    }
}
