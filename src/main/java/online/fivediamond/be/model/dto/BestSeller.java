package online.fivediamond.be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BestSeller {
    long productLineId;
    long quantity;
}
