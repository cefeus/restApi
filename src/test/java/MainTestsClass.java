import Models.PostsModel;
import Models.userModel.UsersModel;
import Utils.APIRequestsAdapter;
import Utils.JsonConvertingUtils;
import Utils.RandomGeneratingMethods;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Properties;

public class MainTestsClass {

    public  ISettingsFile data = new JsonSettingsFile("testData.json");
    public  ISettingsFile config = new JsonSettingsFile("config.json");

    private ResponseEntity response = null;

    @Test
    public void APITest()
    {

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("./.properties");
            property.load(fis);
            String host = property.getProperty("test.param");
            System.out.println(host);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }


        int id = 0;
        AbstractMap.SimpleEntry<PostsModel[],Integer> firstStepResponse = APIRequestsAdapter.GETRequestForAllPosts();
        Assert.assertEquals(firstStepResponse.getValue(), Integer.parseInt(data.getValue("/getStatusCode_success").toString()), "Status code "+ firstStepResponse.getValue() +" isn't equals to " + data.getValue("/getStatusCode_success"));
        Assert.assertNotNull(firstStepResponse.getKey(), "Response isn't a json");
        for (PostsModel model: firstStepResponse.getKey()) {
           Assert.assertTrue(Integer.parseInt(model.getId()) > id, "Posts aren't sorted ascending by id");
           id = Integer.parseInt(model.getId());
        }

        AbstractMap.SimpleEntry<PostsModel,Integer> secondStepResponse = APIRequestsAdapter.GETRequestForPostsByID(data.getValue("/secondStepPostId").toString());
        Assert.assertEquals(secondStepResponse.getValue(), Integer.parseInt(data.getValue("/getStatusCode_success").toString()), "Status code" + secondStepResponse.getValue() + "isn't equals to expected one = " + data.getValue("/getStatusCode_success"));
        PostsModel model = new PostsModel(data.getValue("/secondStep_UserID").toString(), data.getValue("/secondStep_Id").toString(), data.getValue("/secondStep_UserID").toString(), data.getValue("/secondStep_Id").toString());
        Assert.assertTrue(secondStepResponse.getKey().equalsTitleAndBodyNotNull(model));

        AbstractMap.SimpleEntry<PostsModel,Integer> thirdStepResponse = APIRequestsAdapter.GETRequestForPostsByID(data.getValue("/thirdStepPostId").toString());
        Assert.assertEquals(thirdStepResponse.getValue(), Integer.parseInt(data.getValue("/getStatusCode_notFound").toString()), "Status code" + thirdStepResponse.getValue() + "isn't equals to expected one = " + data.getValue("/getStatusCode_notFound"));
        Assert.assertNull(thirdStepResponse.getKey().getBody(), "Response body isn't empty ");

        PostsModel post = new PostsModel(data.getValue("/fourthStep_UserId").toString(), "", RandomGeneratingMethods.getRandomString(10, true, false), RandomGeneratingMethods.getRandomString(10, true, false));
        AbstractMap.SimpleEntry<PostsModel,Integer> fourthStepResponse = APIRequestsAdapter.POSTRequestToCreatePost(post);
        Assert.assertEquals(fourthStepResponse.getValue(), Integer.parseInt(data.getValue("/postStatusCode_success").toString()), "Status code" + fourthStepResponse.getValue() + "isn't equals to expected one = " + data.getValue("/getStatusCode_success"));
        Assert.assertTrue(post.equalWithoutId(fourthStepResponse.getKey()), "Sent post and returned aren't equals");
        Assert.assertNotNull(fourthStepResponse.getKey().getId());

        AbstractMap.SimpleEntry<UsersModel[], Integer> fifthStepResponse = APIRequestsAdapter.GETRequestForAllUsers();
        UsersModel sample = JsonConvertingUtils.convertJsonToObjectFromFile(UsersModel.class, config.getValue("/pathToSampleUser").toString());
        Assert.assertEquals(fifthStepResponse.getValue(), Integer.parseInt(data.getValue("/getStatusCode_success").toString()), "Status code" +fifthStepResponse.getValue()+ "isn't equals to expected one = " + data.getValue("/getStatusCode_success"));
        Assert.assertEquals(sample.toString(), fifthStepResponse.getKey()[Integer.parseInt(data.getValue("/fifthStepUserId").toString()) - 1].toString(), "User data isn't equals to sample one");

        AbstractMap.SimpleEntry<UsersModel, Integer> sixthStepResponse = APIRequestsAdapter.GETRequestForUsersByID(data.getValue("/sixthStepUserId").toString());
        UsersModel sampleUser = JsonConvertingUtils.convertJsonToObjectFromFile(UsersModel.class, config.getValue("/pathToSampleUser").toString());
        Assert.assertEquals(sixthStepResponse.getValue(), Integer.parseInt(data.getValue("/getStatusCode_success").toString()), "Status code" + sixthStepResponse.getValue() + "isn't equals to expected one = " + data.getValue("/getStatusCode_success"));
        Assert.assertEquals(sampleUser.toString(), sixthStepResponse.getKey().toString(), "User data isn't equals to sample one");
    }
}
