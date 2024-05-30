package online.fivediamond.be.service;

import online.fivediamond.be.entity.Account;
import online.fivediamond.be.model.AccountResponse;
import online.fivediamond.be.model.LoginRequest;
import online.fivediamond.be.model.RegisterRequest;
import online.fivediamond.be.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {
    //xu li logic

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    TokenService tokenService;
    public Account register(RegisterRequest registerRequest) {
        //xu li logic register
        Account account = new Account();
        account.setFirstname(registerRequest.getFirstname());
        account.setLastname(registerRequest.getLastname());
        account.setEmail(registerRequest.getEmail());
        account.setPhone(registerRequest.getPhone());
        account.setAddress(registerRequest.getAddress());
        account.setGender(registerRequest.getGender());
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        //nho repo save data xuong db
        return authenticationRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return authenticationRepository.findAll();
    }

    public AccountResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        //=> account chuan
        Account account = (Account) authentication.getPrincipal();
        String token = tokenService.generateToken(account);
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setEmail(account.getEmail());
        accountResponse.setPhone(account.getPhone());
        accountResponse.setId(account.getId());
        accountResponse.setToken(token);
        return accountResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authenticationRepository.findAccountByEmail(email);
    }
}
