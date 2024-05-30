package online.fivediamond.be.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

@Data
public class DiamondUpdateRequest {
    @Column(unique = true)
    long giaReportNumber;
    String imgURL1;
    String imgURL2;
    String imgURL3;
    String imgURL4;
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
