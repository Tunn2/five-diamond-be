package online.fivediamond.be.model.account;

import lombok.Data;
import online.fivediamond.be.enums.Gender;

import java.util.Date;

@Data
public class AccountUpdateRequest {
    String firstname;
    String lastname;
    String phone;
    String address;
    Gender gender;
    Date dob;
}
