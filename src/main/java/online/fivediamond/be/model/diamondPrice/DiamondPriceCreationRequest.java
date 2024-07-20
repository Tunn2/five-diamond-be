package online.fivediamond.be.model.diamondPrice;

import lombok.Data;

@Data
public class DiamondPriceCreationRequest {
    String size;
    String color;
    String clarity;
    String price;
}
