package online.fivediamond.be.model.order;

import lombok.Data;
import online.fivediamond.be.enums.OrderStatus;

import java.time.LocalDate;

@Data
public class OrderCreationRequest {
    String phone;
    String fullname;
    LocalDate orderDate;
    String note;
    String address;
    String promotionCode;
    double totalAmount;
    OrderStatus orderStatus = OrderStatus.PENDING;
}
