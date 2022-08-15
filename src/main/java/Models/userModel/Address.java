package Models.userModel;

import lombok.*;

@Getter
@Setter
@ToString
public class Address {
    private String street;
    private  String suite;
    private String city;
    private String zipcode;
   private Geo geo;
}
