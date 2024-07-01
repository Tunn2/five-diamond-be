package online.fivediamond.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    double price;
}
