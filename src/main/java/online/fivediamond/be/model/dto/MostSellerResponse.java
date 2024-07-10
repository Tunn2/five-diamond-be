package online.fivediamond.be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MostSellerResponse {
    int month;
    List<BestSeller> bestSellerProductLines;
}
