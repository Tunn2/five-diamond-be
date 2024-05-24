package online.fivediamond.be.service;

import online.fivediamond.be.entity.Account;
import online.fivediamond.be.model.RegisterRequest;
import online.fivediamond.be.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    //xu li logic

    @Autowired
    AuthenticationRepository authenticationRepository;

    public Account register(RegisterRequest registerRequest) {
        //xu li logic register
        Account account = new Account();
        account.setPhone(registerRequest.getPhone());
        account.setPassword(registerRequest.getPassword());

        //nho repo save data xuong db
        return authenticationRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return authenticationRepository.findAll();
    }
}
