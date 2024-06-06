package online.fivediamond.be.model;

import lombok.Data;
import online.fivediamond.be.enums.Type;

@Data
public class MaterialUpdateRequest {
    String shape;
    double size;
    String color;
    String clarity;
    double carat;
    String cut;
    String imgURL;
    long certificateID;
    String origin;
    Type type;
    String metal;
    String karat;
    double weight;
    double price;
    String typeOfSub = "Diamond";
    double weightOfSub;
    int quantityOfSub;
}
