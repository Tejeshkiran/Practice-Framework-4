package DataPac;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JsonToListofHashMap {

    public List<HashMap<String, String>> dataReader(String filePath) throws IOException {
        //read Json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath));
        ObjectMapper mapper  = new ObjectMapper();
        //convert string to list of hasmap -> Jackson DataBind
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
        return data;


    }
}
