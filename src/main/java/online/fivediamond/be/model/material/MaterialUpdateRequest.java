package online.fivediamond.be.model.material;

import lombok.Data;
import online.fivediamond.be.enums.Origin;
import online.fivediamond.be.enums.Type;
import online.fivediamond.be.enums.TypeOfSub;

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
    Origin origin;
    Type type;
    String metal;
    String karat;
    double weight;
    double price;
    TypeOfSub typeOfSub;
    double caratOfSub;
    int quantityOfSub;
}
