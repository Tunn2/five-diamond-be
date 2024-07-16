package online.fivediamond.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.fivediamond.be.enums.GoldEnum;

@Entity
@Getter
@Setter
public class Gold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Enumerated(EnumType.STRING)
    GoldEnum goldEnum;
    double pricePerTael;
}
