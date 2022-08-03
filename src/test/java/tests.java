import Models.postsModel;
import Utils.JsonConvertingUtils;
import Utils.RestAPIUtils;
import aquality.selenium.core.configurations.ILoggerConfiguration;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.slf4j.ILoggerFactory;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

public class tests {

    public ISettingsFile file = new JsonSettingsFile("testData.json");

    @Test
    public void test1()
    {
        int id = 0;
        ResponseEntity response = RestAPIUtils.sendGetRequest(file.getValue("/mainUrl").toString() + file.getValue("/allPostsUrl").toString());
        Assert.assertEquals(response.getStatusCodeValue(), 200);
        postsModel[] postModel = JsonConvertingUtils.convertJsonToObject(postsModel[].class, response);
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
        postsModel postsModel = JsonConvertingUtils.convertJsonToObject(Models.postsModel.class,response);
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
        Assert.assertNull(response.getBody());
    }
}
