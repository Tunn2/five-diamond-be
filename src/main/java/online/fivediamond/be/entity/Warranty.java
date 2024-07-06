package online.fivediamond.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    LocalDate orderDate;
    LocalDate expiredDate;
}
