package online.fivediamond.be.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.fivediamond.be.enums.OrderStatus;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    Date orderDate;
    String note;
    double totalAmount;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus = OrderStatus.PENDING;

    @JsonIgnore
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

}
