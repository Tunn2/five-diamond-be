package online.fivediamond.be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String imgURL1;
    String imgURL2;
    String imgURL3;
    String imgURL4;
    String description;
    String type;
    String metal;
    String karat;
    String weightOfType;
    String sideStones;
    double weightOfSideStones;
    int quantityOfSideStones;
    Date importDate;
    long giaReportNumber;
    String shape;
    double carat;
    String color;
    String clarity;
    String cut;
    Date dateOfIssues;

    double cost;
    double price;

}
