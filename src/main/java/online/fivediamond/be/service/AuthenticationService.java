package online.fivediamond.be.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import online.fivediamond.be.entity.Account;
import online.fivediamond.be.entity.Cart;
import online.fivediamond.be.enums.Role;
import online.fivediamond.be.exception.AuthException;
import online.fivediamond.be.exception.BadRequestException;
import online.fivediamond.be.model.account.*;
import online.fivediamond.be.repository.AuthenticationRepository;
import online.fivediamond.be.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    EmailService emailService;

    @Autowired
    TokenService tokenService;

    @Autowired
    CartRepository cartRepository;

    public Account register(RegisterRequest registerRequest) {
        //xu li logic register
        Account account = new Account();
        account.setFirstname(registerRequest.getFirstname());
        account.setRole(registerRequest.getRole());
        account.setDob(registerRequest.getDob());
        account.setLastname(registerRequest.getLastname());
        account.setEmail(registerRequest.getEmail());
        account.setPhone(registerRequest.getPhone());
        account.setAddress(registerRequest.getAddress());
        account.setGender(registerRequest.getGender());
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        account.setCreateAt(LocalDate.now());
        if(account.getRole() == Role.CUSTOMER) {
            Cart cart = new Cart();
            cart = cartRepository.save(cart);
            account.setCart(cart);
        }
        account = authenticationRepository.save(account);

        return account;
    }

    public List<Account> getAllAccounts() {
        return authenticationRepository.findAll();
    }

    public AccountResponse login(LoginRequest loginRequest) {
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
            ));
        } catch (Exception e) {
            throw new AuthException("Wrong Email Or Password");
        }
        Account account = (Account) authentication.getPrincipal();

        String token = tokenService.generateToken(account);
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setEmail(account.getEmail());
        accountResponse.setToken(token);
        accountResponse.setPhone(account.getPhone());
        accountResponse.setDob(account.getDob());
        accountResponse.setFirstname(account.getFirstname());
        accountResponse.setLastname(account.getLastname());
        accountResponse.setRole(account.getRole());
        accountResponse.setGender(account.getGender());
        accountResponse.setRewardPoint(account.getRewardPoint());
        accountResponse.setAddress(account.getAddress());
        accountResponse.setCart(account.getCart());
        return accountResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return authenticationRepository.findAccountByEmail(email);
    }

    public AccountResponse loginGoogle(LoginGoogleRequest loginGoogleRequest) {
        AccountResponse accountResponse = new AccountResponse();
        try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(loginGoogleRequest.getToken());
            String email = firebaseToken.getEmail();
            Account account = authenticationRepository.findAccountByEmail(email);
            if(account == null) {
                account = new Account();
                account.setFirstname(firebaseToken.getName());
                account.setEmail(email);
                account.setRole(Role.CUSTOMER);
                Cart cart = new Cart();
                cart = cartRepository.save(cart);
                account.setCart(cart);
                account =authenticationRepository.save(account);
            }

            String token = tokenService.generateToken(account);
            accountResponse.setId(account.getId());
            accountResponse.setEmail(account.getEmail());
            accountResponse.setToken(token);
            accountResponse.setPhone(account.getPhone());
            accountResponse.setDob(account.getDob());
            accountResponse.setFirstname(account.getFirstname());
            accountResponse.setLastname(account.getLastname());
            accountResponse.setRole(account.getRole());
            accountResponse.setGender(account.getGender());
            accountResponse.setRewardPoint(account.getRewardPoint());
            accountResponse.setAddress(account.getAddress());
            accountResponse.setCart(account.getCart());
        } catch (FirebaseAuthException ex) {
            ex.printStackTrace();
        }

        return accountResponse;
    }

    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        Account account = authenticationRepository.findAccountByEmail(forgotPasswordRequest.getEmail());
        if(account == null) {
            try {
                throw new BadRequestException("Email not found");
            }catch (RuntimeException ex) {
                throw new RuntimeException(ex);
            }
        }
        EmailDetail emailDetail = new EmailDetail();
        emailDetail.setFullName(account.getFirstname());
        emailDetail.setRecipient(account.getEmail());
        emailDetail.setSubject("Reset password for account: " + account.getEmail());
        emailDetail.setMsgBody("");
        emailDetail.setButtonValue("Reset password");
        emailDetail.setLink("http://fivediamond/doi-mat-khau?token=" + tokenService.generateToken(account));
        Runnable r = new Runnable() {
            @Override
            public void run() {
                emailService.sendMailTemplate(emailDetail);
            }
        };
        new Thread(r).start();
    }

    public Account getCurrentAccount() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Account resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Account account = getCurrentAccount();
        account.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
        return authenticationRepository.save(account);
    }

    public Account update(long id, AccountUpdateRequest request) {
        Account account = authenticationRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        account.setFirstname(request.getFirstname());
        account.setLastname(request.getLastname());
        account.setPhone(request.getPhone());
        account.setAddress(request.getAddress());
        account.setGender(request.getGender());
        account.setDob(request.getDob());
        return authenticationRepository.save(account);
    }



}
