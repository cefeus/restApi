package Utils;

import Models.PostsModel;
import Models.userModel.UsersModel;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.springframework.http.ResponseEntity;
import java.util.AbstractMap;


public class APIRequestsAdapter {

    private static ResponseEntity response = null;
    public static ISettingsFile file = new JsonSettingsFile("config.json");

    public static AbstractMap.SimpleEntry GETRequestForAllPosts()
    {
        response = RestAPIUtils.sendGetRequest(file.getValue("/mainUrl").toString() + file.getValue("/allPostsUrl").toString());
        PostsModel[] posts = JsonConvertingUtils.convertJsonToObjectFromString(PostsModel[].class, response.getBody().toString());
        AbstractMap.SimpleEntry<PostsModel[],Integer> map = new AbstractMap.SimpleEntry<>(posts, response.getStatusCodeValue());
        return map;
    }

    public static  AbstractMap.SimpleEntry GETRequestForPostsByID(String id)
    {
        response = RestAPIUtils.sendGetRequest(file.getValue("/mainUrl").toString() + file.getValue("/allPostsUrl").toString() + id);
        PostsModel post = JsonConvertingUtils.convertJsonToObjectFromString(PostsModel.class, response.getBody().toString());
        AbstractMap.SimpleEntry<PostsModel,Integer> map = new AbstractMap.SimpleEntry<>(post, response.getStatusCodeValue());
        return  map;
    }

    public static AbstractMap.SimpleEntry POSTRequestToCreatePost(PostsModel postModel)
    {
        response = RestAPIUtils.sendPostRequest(file.getValue("/mainUrl").toString() + file.getValue("/allPostsUrl").toString(), postModel);
        PostsModel postsModel = JsonConvertingUtils.convertJsonToObjectFromString(PostsModel.class, response.getBody().toString());
        AbstractMap.SimpleEntry<PostsModel,Integer> map = new AbstractMap.SimpleEntry<>(postsModel, response.getStatusCodeValue());
        return map;
    }

    public static AbstractMap.SimpleEntry GETRequestForUsersByID(String id)
    {
        response = RestAPIUtils.sendGetRequest(file.getValue("/mainUrl").toString() + file.getValue("/allUsersUrl").toString() + id);
        UsersModel user = JsonConvertingUtils.convertJsonToObjectFromString(UsersModel.class, response.getBody().toString());
        AbstractMap.SimpleEntry<UsersModel,Integer> map = new AbstractMap.SimpleEntry<>(user, response.getStatusCodeValue());
        return map;
    }

    public static AbstractMap.SimpleEntry GETRequestForAllUsers()
    {
        response = RestAPIUtils.sendGetRequest((file.getValue("/mainUrl").toString() + file.getValue("/allUsersUrl").toString()));
        UsersModel[] users = JsonConvertingUtils.convertJsonToObjectFromString(UsersModel[].class, response.getBody().toString());
        AbstractMap.SimpleEntry<UsersModel[],Integer> map = new AbstractMap.SimpleEntry<>(users, response.getStatusCodeValue());
        return map;
    }

}


