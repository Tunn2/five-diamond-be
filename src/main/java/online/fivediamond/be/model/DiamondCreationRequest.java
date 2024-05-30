package online.fivediamond.be.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;

@Data
public class DiamondCreationRequest {
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
