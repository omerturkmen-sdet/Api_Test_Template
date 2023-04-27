package utils.productsApi;

import com.google.gson.Gson;
import pojos.Pojo;
import pojos.product.AddProductReq;
import pojos.product.AddProductResp;
import steps.BaseStep;
import utils.requestBodyCreation.PojoUtils;
import utils.responseValidation.GsonReader;
import utils.responseValidation.IResponseValidatable;

import static org.junit.Assert.assertEquals;

public class ProductResponseValidation implements IResponseValidatable {
    private AddProductResp responseBody = (AddProductResp) new GsonReader(new AddProductResp()).getResponse();
    @Override
    public void responseValidation() {
        System.out.println("responseBody.id = " + responseBody.id);
        System.out.println("responseBody.brand = " + responseBody.brand);
    }
}
