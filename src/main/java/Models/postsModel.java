package Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostsModel {
    private String userId;
    private String id;
    private String title;
    private String body;

    public boolean equalWithoutId(PostsModel model)
    {
        if((this.userId.compareTo(model.getUserId()) == 0)  &&
                (this.title.compareTo(model.getTitle()) == 0)  &&
                (this.body.compareTo(model.getBody()) == 0)) return true;
        else return false;
    }

    public boolean equalsTitleAndBodyNotNull(PostsModel model)
    {
        if((this.userId.compareTo(model.getUserId()) == 0)  &&
                (this.id.compareTo(model.getId()) == 0) &&
                (this.title != null) &&
                (this.body != null) &&
                (model.body != null) &&
                (model.title != null)) return true;
        else return false;
    }
}
