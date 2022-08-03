package Utils;

import Models.postsModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

public class JsonConvertingUtils {

    private static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T convertJsonToObject(Class<T> cl, ResponseEntity responseEntity)
    {
        try {
            return  mapper.readValue(responseEntity.getBody().toString(), cl);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
