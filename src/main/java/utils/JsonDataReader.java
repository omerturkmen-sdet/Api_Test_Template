package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import pojos.employee.EmployeeCreateRequest;
import pojos.product.AddProductRequest;

public class JsonDataReader {

    private final String path = "src/test/resources/testData/";
    private EmployeeCreateRequest employeeCreateRequest;
    private AddProductRequest addProductRequest;

    /**
     * We will use this method for using pojo class object to reach fields
     *
     * @return pojo class object
     */
    public final EmployeeCreateRequest getEmployeeCreateRequest(){
        employeeCreateRequest = getEmployeeRequestPojoData();
        return employeeCreateRequest;
    }


    /**
     * This method will assign pojo class' fields with json file
     *
     * @return Pojo class with assigned value
     */
    private EmployeeCreateRequest getEmployeeRequestPojoData(){
        String jsonBodyPath = path + "createEmployee.json";
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(jsonBodyPath));
            employeeCreateRequest = gson.fromJson(bufferReader, EmployeeCreateRequest.class);
            return employeeCreateRequest;
        }catch(FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + path);
        }finally {
            try { if(bufferReader != null) bufferReader.close();}
            catch (IOException ignore) {}
        }
    }


    public final AddProductRequest getAddProductRequest(){
        addProductRequest = getAddProductData();
        return addProductRequest;
    }


    /**
     * This method will assign pojo class' fields with json file
     *
     * @return Pojo class with assigned value
     */
    private AddProductRequest getAddProductData(){
        String jsonBodyPath = path + "addProduct.json";
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(jsonBodyPath));
            addProductRequest = gson.fromJson(bufferReader, AddProductRequest.class);
            return addProductRequest;
        }catch(FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + path);
        }finally {
            try { if(bufferReader != null) bufferReader.close();}
            catch (IOException ignore) {}
        }
    }
}
