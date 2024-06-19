package online.fivediamond.be.model.account;

import lombok.Data;
import online.fivediamond.be.entity.Account;
@Data
public class AccountResponse extends Account {
    String token;
}
