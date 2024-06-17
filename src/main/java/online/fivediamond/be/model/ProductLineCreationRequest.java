package online.fivediamond.be.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductLineCreationRequest {
    String name;
    String description;
    List<Long> productID;
}
