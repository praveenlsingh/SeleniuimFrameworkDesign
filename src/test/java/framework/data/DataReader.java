package framework.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        //Json to String
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//framework//data//PurchaseOrder.json"), StandardCharsets.UTF_8);

        //String to Hashmap Jackson Data bind
        ObjectMapper mapper = new ObjectMapper();
        //Based on index in Data we are putting Hashmap in list and give to data Hashmap Lisi
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
        });
        //data = {map,map}
        return data;

    }
}
