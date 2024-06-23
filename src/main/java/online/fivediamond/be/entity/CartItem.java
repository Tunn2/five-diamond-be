package online.fivediamond.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "product_line_id")
    ProductLine productLine;

    int quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;
}
