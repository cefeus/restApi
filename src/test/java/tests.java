import Models.postsModel;
import Utils.JsonConvertingUtils;
import Utils.RestAPIUtils;
import Utils.randomGeneratingMethods;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;
import userModel.usersModel;

public class tests {

    public ISettingsFile file = new JsonSettingsFile("testData.json");

    @Test
    public void test1()
    {
        int id = 0;
        ResponseEntity response = RestAPIUtils.sendGetRequest(file.getValue("/mainUrl").toString() + file.getValue("/allPostsUrl").toString());
        Assert.assertEquals(response.getStatusCodeValue(), 200);
        postsModel[] postModel = JsonConvertingUtils.convertJsonToObjectFromString(postsModel[].class, response.getBody().toString());
        System.out.println(response.getStatusCodeValue() + '\n');
        System.out.println(postModel[1].toString());
        Assert.assertTrue(postModel != null);
        for (postsModel model: postModel) {
            Assert.assertTrue(Integer.parseInt(model.getId()) > id);
            id = Integer.parseInt(model.getId());
        }
    }

    @Test
    public void test2()
    {
        ResponseEntity response = RestAPIUtils.sendGetRequest(file.getValue("/mainUrl").toString() + file.getValue("/allPostsUrl").toString() + file.getValue("/secondStepPostId"));
        Assert.assertEquals(response.getStatusCodeValue(), 200);
        postsModel postsModel = JsonConvertingUtils.convertJsonToObjectFromString(Models.postsModel.class,response.getBody().toString());
        Assert.assertEquals(postsModel.getId(), file.getValue("/secondStep_Id"));
        Assert.assertEquals(postsModel.getUserId(), file.getValue("/secondStep_UserID"));
        Assert.assertNotNull(postsModel.getBody());
        Assert.assertNotNull(postsModel.getTitle());
    }

    @Test
    public void test3()
    {
        ResponseEntity response = RestAPIUtils.sendGetRequest(file.getValue("/mainUrl").toString() + file.getValue("/allPostsUrl").toString() + file.getValue("/thirdStepPostId"));
        Assert.assertEquals(response.getStatusCodeValue(), 404);
        Assert.assertEquals(response.getBody(), "{}");
    }

    @Test
    public void test4()
    {
        postsModel postModel = new postsModel(file.getValue("/fourthStep_UserId").toString(),"", randomGeneratingMethods.getRandomString(10, true, false), randomGeneratingMethods.getRandomString(10, true, false));
        ResponseEntity response = RestAPIUtils.sendPostRequest(file.getValue("/mainUrl").toString()+ file.getValue("/allPostsUrl").toString(), postModel);
        postsModel responseModel = JsonConvertingUtils.convertJsonToObjectFromString(postsModel.class, response.getBody().toString());
        Assert.assertEquals(postModel.getTitle(), responseModel.getTitle());
        Assert.assertEquals(postModel.getBody(), responseModel.getBody());
        Assert.assertNotNull(responseModel.getId());
        Assert.assertEquals(postModel.getUserId(), responseModel.getUserId());
    }

    @Test
    public void test5()
    {
        usersModel[] users;
        ResponseEntity response = RestAPIUtils.sendGetRequest("https://jsonplaceholder.typicode.com/users");//(file.getValue("/mainUrl").toString() + file.getValue("/allUsersUrl").toString());
        users = JsonConvertingUtils.convertJsonToObjectFromString(usersModel[].class, response.getBody().toString());
        usersModel sample = JsonConvertingUtils.convertJsonToObjectFromFile(usersModel.class, ".\\src\\main\\resources\\sampleUser.json");
        Assert.assertEquals(sample, users[Integer.parseInt(file.getValue("/fifthStepUserId").toString()) - 1]);
    }

    @Test
    public void test6()
    {
        ResponseEntity response = RestAPIUtils.sendGetRequest(file.getValue("/mainUrl").toString() + file.getValue("/allUsersUrl").toString() + file.getValue("/sixthStepUserId"));
        usersModel user = JsonConvertingUtils.convertJsonToObjectFromString(usersModel.class, response.getBody().toString());
        usersModel sample = JsonConvertingUtils.convertJsonToObjectFromFile(usersModel.class, ".\\src\\main\\resources\\sampleUser.json");
        Assert.assertEquals(sample, user);
    }
}
