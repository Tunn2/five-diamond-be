package online.fivediamond.be.model;

import jakarta.persistence.Column;
import lombok.Data;
import online.fivediamond.be.entity.Category;
import online.fivediamond.be.entity.Material;

import java.sql.Date;
import java.util.List;

@Data
public class ProductCreationRequest {

    double priceRate;
    String imgURL;
    double weight;
    List<Long> materialID;
    long categoryID;
    boolean isDeleted = false;

}
