package online.fivediamond.be.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CategoryCreationRequest {

    String name;
    String description;
}
