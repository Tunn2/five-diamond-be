package online.fivediamond.be.model.gold;

import lombok.Data;
import online.fivediamond.be.enums.GoldEnum;

@Data
public class GoldCreationRequest {
    String gold;
    double pricePerTael;
}
