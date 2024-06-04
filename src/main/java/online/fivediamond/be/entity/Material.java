package online.fivediamond.be.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import online.fivediamond.be.enums.Type;

@Entity
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(unique = true)
    long giaReportNumber;
    String shape;
    double size;
    String color;
    String clarity;
    double carat;
    String cut;
    String imgURL;
    String name;
    Type type;
    String metal;
    String karat;
    double weight;
    int quantityOfSub;
    int quantity;
    double price;
}

