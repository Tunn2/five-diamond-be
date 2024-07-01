package online.fivediamond.be.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    String code;
    double discountPercentage;
    int quantity;
    String startDate;
    String endDate;
    boolean isDeleted;
}
