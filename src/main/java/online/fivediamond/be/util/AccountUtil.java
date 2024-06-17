package online.fivediamond.be.util;

import online.fivediamond.be.entity.Account;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AccountUtil {
    public Account accountCurrent() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
