package online.fivediamond.be.model;

import lombok.Data;

@Data
public class RegisterRequest {
    String firstname;
    String lastname;
    String email;
    String phone;
    String address;
    String role = "customer";
    String gender;
    String password;
}
