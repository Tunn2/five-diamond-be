package online.fivediamond.be.model.account;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
