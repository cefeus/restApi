package Models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class postsModel {
    private String userId;
    private String id;
    private String title;
    private String body;

}
