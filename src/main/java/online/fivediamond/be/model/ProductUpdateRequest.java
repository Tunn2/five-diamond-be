package online.fivediamond.be.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ProductUpdateRequest {
    double priceRate;
    String imgURL;
    double weight;
    List<Long> materialID;
    long categoryID;
    boolean isDeleted = false;
}
