package online.fivediamond.be.model.collection;

import lombok.Data;

import java.util.List;

@Data
public class CollectionUpdateRequest {
    String name;
    String description;
    String imgURL;
}
