package online.fivediamond.be.model;

import lombok.Data;

import java.util.Date;

@Data
public class CreatePromotionRequest {
    String name;
    String description;
    double discountPercentage;
    String imgURL;
    Date startDate;
    Date endDate;
}
