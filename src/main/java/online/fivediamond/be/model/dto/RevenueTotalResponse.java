package online.fivediamond.be.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.fivediamond.be.entity.Order;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RevenueTotalResponse {
    int month;
    double totalRevenue;
    double totalProfit;
    List<Order> list;
}
