package online.fivediamond.be.util;

import online.fivediamond.be.entity.Account;
import online.fivediamond.be.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AccountUtil {

    @Autowired
    AuthenticationRepository authenticationRepository;

    public Account accountCurrent() {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authenticationRepository.findAccountByEmail(account.getEmail());
    }
}
