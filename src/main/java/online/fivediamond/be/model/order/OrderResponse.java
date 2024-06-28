package online.fivediamond.be.model.order;

import online.fivediamond.be.entity.Order;
import online.fivediamond.be.entity.OrderItem;

import java.util.Set;

public class OrderResponse extends Order {
    Set<OrderItem> orderItems;
}
