package utils.responseValidation;

import com.google.gson.Gson;
import pojos.product.AddProductResp;
import steps.BaseStep;

public interface IResponseValidatable {

    //IResponseValidatable response = gson.fromJson(BaseStep.response.body().asString(), IResponseValidatable.class);

    void responseValidation();
}
