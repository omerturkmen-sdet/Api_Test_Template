package utils;

import com.google.gson.Gson;
import pojos.Pojo;
import pojos.map.AddPlaceReq;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataReader {

    public JsonDataReader(Pojo pojo){
        this.pojo = pojo;
    }
    private final String path = "src/test/resources/testData/";
    Pojo pojo;

    private String getPathFromScenario(){
        if (ScenarioUtil.getScenarioName().contains("Add Place")){
            return path + "addPlace.json";
        }
        return path + "addProduct.json";
    }
    public final Pojo getJson(){
        pojo = getPojoData();
        return pojo;
    }

    private Pojo getPojoData(){
        String jsonBodyPath = getPathFromScenario();
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(jsonBodyPath));
            pojo = gson.fromJson(bufferReader, this.pojo.getClass());
            return pojo;
        }catch(FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + jsonBodyPath);
        }finally {
            try { if(bufferReader != null) bufferReader.close();}
            catch (IOException ignore) {}
        }
    }
}
