package online.fivediamond.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import online.fivediamond.be.enums.Gender;
import online.fivediamond.be.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String firstname;
    String lastname;

    @Column(unique = true)
    String email;

    String phone;
    String address;
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Enumerated(EnumType.STRING)
    Role role;
    double rewardPoint;
    Date dob;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    LocalDate createAt;

    @OneToOne
    @JoinColumn(name = "cart_id")
    Cart cart;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    Set<Order> ordersCus;

    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    Set<Order> ordersStaff;

    @OneToMany(mappedBy = "account")
    Set<CanceledOrder> canceledOrders;

    @JsonIgnore
    @OneToMany(mappedBy = "shipper")
    Set<Order> ordersShip;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    Set<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    Set<Warranty> warranties;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
