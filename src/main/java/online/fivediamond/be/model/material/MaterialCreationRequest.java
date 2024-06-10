package online.fivediamond.be.model.material;

import lombok.Data;
import online.fivediamond.be.enums.Origin;
import online.fivediamond.be.enums.Type;

@Data
public class MaterialCreationRequest {
    //diamond
    String shape;
    double size;
    String color;
    String clarity;
    double carat;
    String cut;
    long certificateID;
    Origin origin;

    //vo kim cuong
    String metal;
    String karat;
    String typeOfSub = "Diamond";
    double caratOfSub;
    int quantityOfSub;

    //diamond va vo kim cuong
    double price;
    String imgURL;
    Type type;
}
