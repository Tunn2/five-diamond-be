package online.fivediamond.be.model.productLine;

import lombok.Data;
import online.fivediamond.be.entity.ProductLine;

import java.util.List;

@Data
public class ProductLineResponse extends ProductLine {
    List<Long> diamondIds;
}
