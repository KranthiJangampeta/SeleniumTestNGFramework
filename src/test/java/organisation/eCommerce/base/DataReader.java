package organisation.eCommerce.base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public List<HashMap<String, String>> getJsonDataToMap(String jsonFileName) throws IOException {
        // read json file as string
        File placeOrder = new File(System.getProperty("user.dir") + "/src/test/resources/TestData/" + jsonFileName + ".json");
        String jsonString = FileUtils.readFileToString(placeOrder);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<HashMap<String, String>>> outputType = new TypeReference<List<HashMap<String, String>>>() {
        };
        List<HashMap<String, String>> hmData = mapper.readValue(jsonString, outputType);

        return hmData;
    }
}
