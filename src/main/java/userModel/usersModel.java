package userModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class usersModel {

    private String id;
    private String name;
    private String username;
    private  String email;
    private address address;
    private String phone;
    private String website;
    private company company;
}

