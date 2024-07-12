package online.fivediamond.be.model.collection;

import lombok.Data;
import online.fivediamond.be.entity.Collection;
import online.fivediamond.be.entity.ProductLine;

import java.util.List;

@Data
public class CollectionResponse extends Collection {

    List<ProductLine> productLinesList;
}
