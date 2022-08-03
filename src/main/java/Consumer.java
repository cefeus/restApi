import Models.postsModel;
import Utils.JsonConvertingUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import Utils.RestAPIUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class Consumer {

    public static void main(String[] args) throws JsonProcessingException {
       RestTemplate rts = new RestTemplate();
        String URL = "https://jsonplaceholder.typicode.com/posts";
//        String URLusers = "https://jsonplaceholder.typicode.com//users";
//        String responce = rt.getForObject(URL, String.class);
//        ResponseEntity<String> responseEntity = rt.exchange (URL, HttpMethod.GET, null,String.class );
//        System.out.println(responseEntity.getStatusCodeValue());
//        System.out.println(responce);
//
//        RestTemplate rest = new RestTemplate();
//        Map<String, String> map = new HashMap<>();
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(map);
//        ResponseEntity<String> stringResponseEntity = rest.postForEntity(URL, entity, String.class);
//        System.out.println(stringResponseEntity);
//
//        String resp = rt.getForObject(URLusers, String.class);
//        System.out.println(responce);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        RestAPIUtils util = new RestAPIUtils();
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> re = rt.exchange(URL, HttpMethod.GET, null, String.class);
        postsModel[] model;
        model = JsonConvertingUtils.convertJsonToObjectFromString(postsModel[].class, re.getBody().toString());

//        ResponseEntity<String> resp = util.sendPostRequest(URL, model[1]);
//        System.out.println(resp);
//        for (postsModel mod : model) {
//            System.out.println(mod);
//          String responseEntity = util.sendGetRequest("https://jsonplaceholder.typicode.com/users");
//        System.out.println(responseEntity);
//        postsModel[] model1;
//        usersModel[] model;
//        String respinse = util.sendGetRequest("https://jsonplaceholder.typicode.com/posts");
//        model1 = mapper.readValue(respinse, postsModel[].class);
//          model = mapper.readValue(responseEntity,usersModel[].class);
        }


//          model = mapper.readValue(responseEntity, postsModel[].class);
//          System.out.println(model[0]);

    }


