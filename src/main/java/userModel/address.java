package userModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class address {

    private String street;
    private  String suite;
    private String city;
    private String zipcode;
   private geo geo;
}
