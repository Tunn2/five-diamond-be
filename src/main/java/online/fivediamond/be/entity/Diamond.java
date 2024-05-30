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
    String type;
    String imgURL;
    double cost;
    double price;
    Date importDate;
    String shape;
    double carat;
    String color;
    String clarity;
    String cut;
    Date dateOfIssues;

}
