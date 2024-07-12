package online.fivediamond.be.model.productLine;

import lombok.Data;
import online.fivediamond.be.enums.Gender;
import online.fivediamond.be.enums.Origin;
import online.fivediamond.be.enums.TypeOfSub;

import java.util.List;

@Data
public class ProductLineCreationRequest {

    String description;
    String name;
    Gender gender;
    double priceRate;
    double weight;
    String metal = "GOLD";
    String karat;
    String imgURL;
    TypeOfSub typeOfSub;
    int quantityOfSub;
    boolean isSpecial;
    long categoryID;
    List<Long> diamondID;
    String shape;
    String cut;
    String color;
    String clarity;
    double carat;
    double size;
    Origin origin;
}
