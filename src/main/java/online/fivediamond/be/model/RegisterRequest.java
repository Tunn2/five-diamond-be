package online.fivediamond.be.model;

import lombok.Data;

import java.sql.Date;

@Data
public class RegisterRequest {
    String firstname;
    String lastname;
    String email;
    String phone;
    String address;
    Date dob;
    String role = "customer";
    String gender;
    String password;
}
