package online.fivediamond.be.model.productLine;

import lombok.Data;
import lombok.EqualsAndHashCode;
import online.fivediamond.be.entity.Collection;
import online.fivediamond.be.entity.ProductLine;

import java.util.List;


@Data
public class ProductLineResponse extends ProductLine {
    List<Long> diamondIds;
    List<Collection> collectionList;
}
