package online.fivediamond.be.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountTotalResponse {
    int month;
    int memberTotal;
    int salesQuantity;
    int customerQuantity;
    int adminQuantity;
    int deliverQuantity;
    int managerQuantity;
}
