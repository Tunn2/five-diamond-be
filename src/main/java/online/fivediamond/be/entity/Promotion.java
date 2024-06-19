package online.fivediamond.be.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    String description;
    double discountPercentage;
    String imgURL;
    Date startDate;
    Date endDate;

}
