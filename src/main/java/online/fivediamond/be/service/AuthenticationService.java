package online.fivediamond.be.service;

import online.fivediamond.be.entity.Account;
import online.fivediamond.be.model.LoginRequest;
import online.fivediamond.be.model.RegisterRequest;
import online.fivediamond.be.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public Account register(RegisterRequest registerRequest) {
        //xu li logic register
        Account account = new Account();
        account.setFirstname(registerRequest.getFirstname());
        account.setLastname(registerRequest.getLastname());
        account.setEmail(registerRequest.getEmail());
        account.setPhone(registerRequest.getPhone());
        account.setAddress(registerRequest.getAddress());
        account.setSex(registerRequest.getSex());
        account.setUsername(registerRequest.getUsername());
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        //nho repo save data xuong db
        return authenticationRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return authenticationRepository.findAll();
    }

    public Account login(LoginRequest loginRequest) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            ));

        //=> account chuan
        return authenticationRepository.findAccountByUsername(loginRequest.getUsername());
    }

    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
        return authenticationRepository.findAccountByUsername(username);
    }

}
