package online.fivediamond.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.fivediamond.be.enums.TypeOfSub;

@Entity
@Setter
@Getter
public class Sub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Enumerated(EnumType.STRING)
    TypeOfSub typeOfSub;
    double price;
}
