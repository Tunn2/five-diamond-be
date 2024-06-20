package online.fivediamond.be.model.order;

import lombok.Data;
import online.fivediamond.be.enums.OrderStatus;

import java.util.Date;
import java.util.List;

@Data
public class OrderCreationRequest {
    String phone;
    String fullname;
    Date orderDate;
    String note;
    String address;
    OrderStatus orderStatus = OrderStatus.PENDING;
    List<Long> productLineId;
}
