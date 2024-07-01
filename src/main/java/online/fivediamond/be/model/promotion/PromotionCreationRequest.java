package online.fivediamond.be.model.promotion;

import lombok.Data;

@Data
public class PromotionCreationRequest {
    String code;
    double discountPercentage;
    int quantity;
    String startDate;
    String endDate;
}
