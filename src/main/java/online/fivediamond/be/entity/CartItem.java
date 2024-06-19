package online.fivediamond.be.entity;

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

    @OneToOne
    @JoinColumn(name = "product_line_id")
    ProductLine productLine;

    int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;
}
