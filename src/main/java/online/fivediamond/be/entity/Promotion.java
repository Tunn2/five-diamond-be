package online.fivediamond.be.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

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
    String startDate;
    String endDate;
    boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL)
    Set<Order> orders;
}
