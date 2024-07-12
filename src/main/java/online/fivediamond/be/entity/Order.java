package online.fivediamond.be.entity;

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
    String imgConfirmUrl;

    int cannotContactTimes;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    CanceledOrder canceledOrder;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    Promotion promotion;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Account customer;

    @ManyToOne
    @JoinColumn(name = "shipper_id")
    Account shipper;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    Account staff;

}
