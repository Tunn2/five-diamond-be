package online.fivediamond.be.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.fivediamond.be.entity.Account;
import online.fivediamond.be.model.AccountResponse;
import online.fivediamond.be.model.EmailDetail;
import online.fivediamond.be.model.LoginRequest;
import online.fivediamond.be.model.RegisterRequest;
import online.fivediamond.be.service.AuthenticationService;
import online.fivediamond.be.service.EmailService;
import online.fivediamond.be.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@SecurityRequirement(name = "api")
public class AuthenticationAPI {
    @Autowired
    AuthenticationService authenticationService;
    //nhan request tu FE

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmailService emailService;

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        Account account = authenticationService.register(registerRequest);
        //account da dc luu xuong db
        return ResponseEntity.ok(account);
    }

    @GetMapping("admin-only")
    @PreAuthorize("hasAuthorize('ADMIN')")
    public ResponseEntity admin() {
        return ResponseEntity.ok("ok");
    }


    @GetMapping("send-mail")
    public void sendMail(){
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setRecipient("ktung7574@gmail.com");
        emailDetail.setSubject("test123");
        emailDetail.setMsgBody("aaa");
        emailService.sendMailTemplate(emailDetail);
    }

    @GetMapping("accounts")
    public ResponseEntity getAllAccounts() {
        List<Account> list = authenticationService.getAllAccounts();
        return ResponseEntity.ok(list);
    }

    @PostMapping("login")
    public AccountResponse login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        Account account = authenticationService.login(loginRequest);
        String token = tokenService.generateToken(account);
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setUsername(account.getUsername());
        accountResponse.setToken(token);
        return accountResponse;
    }
}
