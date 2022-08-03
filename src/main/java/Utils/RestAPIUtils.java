package Utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.StringBufferInputStream;

public class RestAPIUtils {

    private static RestTemplate rt = new RestTemplate();

    public static ResponseEntity sendGetRequest(String URL)
    {
    try{
        ResponseEntity<String> responseEntity = rt.exchange(URL, HttpMethod.GET, null, String.class);
        return responseEntity;
    }
    catch(HttpClientErrorException e)
    {
        ResponseEntity<String> response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        return response;
    }
    }
    public static <T> ResponseEntity sendPostRequest(String URL, T cl)
    {
        HttpEntity<T> entity = new HttpEntity<>(cl);
        ResponseEntity<String> responseEntity = rt.exchange(URL, HttpMethod.POST, entity,  String.class);
        return responseEntity;
    }
}
