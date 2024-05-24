package online.fivediamond.be.api;

import online.fivediamond.be.entity.Account;
import online.fivediamond.be.model.RegisterRequest;
import online.fivediamond.be.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthenticationAPI {
    @Autowired
    AuthenticationService authenticationService;
    //nhan request tu FE

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {

        Account account = authenticationService.register(registerRequest);
        //account da dc luu xuong db
        return ResponseEntity.ok(account);
    }

    @GetMapping("get-all-accounts")
    public ResponseEntity getAllAccounts() {
        List<Account> list = authenticationService.getAllAccounts();
        return ResponseEntity.ok(list);
    }

}
