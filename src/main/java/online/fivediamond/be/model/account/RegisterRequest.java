package online.fivediamond.be.model.account;

import lombok.Data;
import online.fivediamond.be.enums.Gender;
import online.fivediamond.be.enums.Role;

import java.util.Date;


@Data
public class RegisterRequest {
    String firstname;
    String lastname;
    String email;
    String phone;
    String address;
    Date dob;
    Role role = Role.CUSTOMER;
    Gender gender;
    String password;
}
