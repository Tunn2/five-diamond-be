package online.fivediamond.be.model.material;

import lombok.Data;
import online.fivediamond.be.enums.Origin;
import online.fivediamond.be.enums.Type;
import online.fivediamond.be.enums.TypeOfSub;

@Data
public class MaterialCreationRequest {
    //diamond
    String shape;
    double size;
    String color;
    String clarity;
    double carat;
    String cut;
    long giaReportNumber;
    Origin origin;

    //vo kim cuong
    String metal;
    String karat;
    TypeOfSub typeOfSub;
    double caratOfSub;
    int quantityOfSub;

    //diamond va vo kim cuong
    double price;
    String imgURL;
    Type type;
}
