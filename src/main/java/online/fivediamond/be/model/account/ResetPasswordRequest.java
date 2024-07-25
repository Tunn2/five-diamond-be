package online.fivediamond.be.model.account;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ResetPasswordRequest {
    String password;
}
