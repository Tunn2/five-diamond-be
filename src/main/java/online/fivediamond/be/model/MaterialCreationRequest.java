package online.fivediamond.be.model;

import lombok.Data;
import online.fivediamond.be.enums.Type;

@Data
public class MaterialCreationRequest {
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
