package online.fivediamond.be.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.fivediamond.be.enums.OrderStatus;

import java.time.LocalDateTime;
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

    String fullname;
    String phone;
    LocalDateTime orderDate;
    String note;
    String address;
    double totalAmount;
    Date shippingDate;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

}
