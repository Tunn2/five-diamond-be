package online.fivediamond.be.model.order;

import lombok.Data;
import online.fivediamond.be.enums.OrderStatus;

@Data
public class OrderStatusUpdateRequest {
    OrderStatus orderStatus;
}
