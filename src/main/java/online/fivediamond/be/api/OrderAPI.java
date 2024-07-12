package online.fivediamond.be.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.fivediamond.be.entity.Order;
import online.fivediamond.be.model.order.*;
import online.fivediamond.be.service.OrderService;
import online.fivediamond.be.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@SecurityRequirement(name="api")
public class OrderAPI {

    @Autowired
    OrderService orderService;

    @Autowired
    WalletService walletService;

    @PostMapping
    public ResponseEntity create(@RequestBody OrderCreationRequest request) {
        return ResponseEntity.ok(orderService.convertCartToOrder(request));
    }

    @GetMapping
    public ResponseEntity getOrderByAccountId() {
        return ResponseEntity.ok(orderService.getOrdersByAccountId());
    }

    @GetMapping("all")
    public ResponseEntity getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("{id}")
    public ResponseEntity getOrderById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("pending")
    public ResponseEntity getPendingOrders() {
        return ResponseEntity.ok(orderService.getPendingOrders());
    }

    @GetMapping("confirmed")
    public ResponseEntity getConfirmedOrders() {
        return ResponseEntity.ok(orderService.getConfirmedOrders());
    }

    @GetMapping("processing")
    public ResponseEntity getProcessingOrders() {
        return ResponseEntity.ok(orderService.getProcessingOrders());
    }

    @GetMapping("shipped")
    public ResponseEntity getShippedOrders() {
        return ResponseEntity.ok(orderService.getShippedOrders());
    }

    @GetMapping("delivered")
    public ResponseEntity getDeliveredOrders() {
        return ResponseEntity.ok(orderService.getDeliveredOrders());
    }

    @PutMapping("{id}&{accountID}")
    public ResponseEntity updateOrderStatus(@PathVariable long id, @RequestBody OrderStatusUpdateRequest request, @PathVariable long accountID) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, request, accountID));
    }

    @PutMapping("cancel/{id}")
    public ResponseEntity cancelOrder(@PathVariable long id, @RequestBody OrderCancelRequest request) {
        return ResponseEntity.ok(orderService.cancelOrder(id, request));
    }

    @PutMapping("confirm/{id}")
    public ResponseEntity confirmOrder(@PathVariable long id, @RequestBody OrderDeliveryRequest request) {
        return ResponseEntity.ok(orderService.deliveredOrder(id, request));
    }

    @PutMapping("refund/{id}")
    public ResponseEntity refund(@PathVariable long id) {
        return ResponseEntity.ok(orderService.refund(id));
    }

    @GetMapping("canceled")
    public ResponseEntity getListCanceledOrder() {
        return ResponseEntity.ok(orderService.getListCanceledOrder());
    }

    @PostMapping("cannot-contact/{id}")
    public ResponseEntity cannotContact(@PathVariable long id, @RequestBody OrderCancelRequest request) {
        return ResponseEntity.ok(orderService.cannotContact(id, request));
    }
}
