package utils.responseValidation;

import com.google.gson.Gson;
import pojos.Pojo;
import pojos.product.AddProductResp;
import steps.BaseStep;

public class GsonReader {

    public GsonReader(Pojo pojo){this.pojo = pojo;}
    Pojo pojo;
    Gson gson = new Gson();

    public Pojo getResponse(){
        return gson.fromJson(BaseStep.response.body().asString(), this.pojo.getClass());
    }

}
