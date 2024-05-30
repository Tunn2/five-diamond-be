package online.fivediamond.be.model;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}
