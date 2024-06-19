package online.fivediamond.be.model.diamond;

import lombok.Data;
import online.fivediamond.be.enums.Origin;

@Data
public class DiamondCreationRequest {

    String shape;
    double size;
    String color;
    String clarity;
    double carat;
    String cut;
    long certificateID;
    Origin origin;
    double price;
    String imgURL;
}
