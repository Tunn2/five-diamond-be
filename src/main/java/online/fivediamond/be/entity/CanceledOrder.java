package online.fivediamond.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.fivediamond.be.enums.CancelOrderStatus;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class CanceledOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    Order order;


    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

    LocalDate cancelDate;
    String reason;
    double refundAmount;

    @Enumerated(value = EnumType.STRING)
    CancelOrderStatus cancelOrderStatus;
}
