package online.fivediamond.be.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

@Data
public class ProductUpdateRequest {
    String name;
    String imgURL1;
    String imgURL2;
    String imgURL3;
    String imgURL4;
    String description;
    long categoryID;
    Date importDate;
    @Column(unique = true)
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
