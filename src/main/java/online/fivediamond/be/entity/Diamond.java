package online.fivediamond.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
public class Diamond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    long giaReportNumber;
    String imgURL1;
    String imgURL2;
    String imgURL3;
    String imgURL4;
    double cost;
    double price;
    Date importDate;
    double size;
    String shape;
    double carat;
    String color;
    String clarity;
    String cut;
    Date dateOfIssues;

}
