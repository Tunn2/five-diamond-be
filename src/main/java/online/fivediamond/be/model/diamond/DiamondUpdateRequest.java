package online.fivediamond.be.model.diamond;

import lombok.Data;
import online.fivediamond.be.enums.Origin;
import online.fivediamond.be.enums.Type;
import online.fivediamond.be.enums.TypeOfSub;

@Data
public class DiamondUpdateRequest {
    String shape;
    double size;
    String color;
    String clarity;
    double carat;
    String cut;
    long giaReportNumber;
    Origin origin;
    double price;
    String imgURL;
}
