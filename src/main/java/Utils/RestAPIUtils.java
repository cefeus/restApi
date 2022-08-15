package Utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestAPIUtils {

    private static RestTemplate rt = new RestTemplate();

    public static ResponseEntity sendGetRequest(String URL)
    {
        ResponseEntity<String> responseEntity;
        try{
            responseEntity = rt.exchange(URL, HttpMethod.GET, null, String.class);
        return responseEntity;
        }
        catch(HttpClientErrorException.NotFound e)
        {
            responseEntity = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
            return responseEntity;
        }
    }

    public static <T> ResponseEntity sendPostRequest(String URL, T cl)
    {
        HttpEntity<T> entity = new HttpEntity<>(cl);
        ResponseEntity<String> responseEntity = rt.postForEntity(URL, entity,  String.class);
        return responseEntity;
    }
}
