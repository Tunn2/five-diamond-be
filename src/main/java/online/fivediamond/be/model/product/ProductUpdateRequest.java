package online.fivediamond.be.model.product;

import lombok.Data;

import java.util.List;

@Data
public class ProductUpdateRequest {
    double priceRate;
    String imgURL;
    double weight;
    List<Long> materialID;
    long categoryID;
    boolean isSpecial;
}
