package utils.responseValidation;

import com.google.gson.Gson;
import pojos.employee.CreateEmployeeResponse;
import pojos.product.AddProductRequest;
import pojos.product.AddProductResponse;
import steps.BaseStep;
import utils.requestBodyCreation.AddProductUtils;
import utils.requestBodyCreation.PojoUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ProductResponseValidation {
    Gson gson = new Gson();

    //This will convert response to pojo object
     AddProductResponse response = gson.fromJson(BaseStep.response.body().asString(), AddProductResponse.class);


    public void productResponseValidation() {
        AddProductRequest request = PojoUtils.getInstance().getAddProductUtils().addProductRequest;
        assertEquals(request.getBrand(),response.getBrand());

        //Request title is from json file, but we changed while sending request. So they should be different
        assertNotEquals(request.getTitle(),response.getTitle());
    }
}
