package online.fivediamond.be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DiamondPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String size;
    String color;
    String cut;
    String price;
}
