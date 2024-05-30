package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthenticationRepository extends JpaRepository<Account, Long> {
    Account findAccountByPhone(String phone);
    Account findAccountByEmail(String email);
}
