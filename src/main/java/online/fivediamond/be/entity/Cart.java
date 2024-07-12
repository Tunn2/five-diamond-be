package online.fivediamond.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Setter
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne(mappedBy = "cart",cascade = CascadeType.ALL)
    @JsonIgnore
    Account account;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    Set<CartItem> cartItems;

}
