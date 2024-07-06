package online.fivediamond.be.repository;

import online.fivediamond.be.entity.Account;
import online.fivediamond.be.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthenticationRepository extends JpaRepository<Account, Long> {
    Account findAccountByPhone(String phone);
    Account findAccountByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM account WHERE role = :role", nativeQuery = true)
    int countByRole(@Param("role") String role);

    @Query(value = "SELECT COUNT(*) FROM account WHERE role = :role AND MONTH(create_at) = :month AND YEAR(create_at) = :year", nativeQuery = true)
    int countByRoleByMonth(@Param("role") String role, @Param("month") int month, @Param("year") int year);
}
