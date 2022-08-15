package Utils;

import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JsonConvertingUtils {

    private static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T convertJsonToObjectFromString(Class<T> cl, String source)
    {
        try {
            return  mapper.readValue(source, cl);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T convertJsonToObjectFromFile(Class<T> cl, String path)
    {
        try {
            return  mapper.readValue(new File(path), cl);
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getInstance().error(e.getMessage());
        }
        return null;
    }
}
