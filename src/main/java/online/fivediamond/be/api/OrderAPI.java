package online.fivediamond.be.api;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.fivediamond.be.model.order.OrderCreationRequest;
import online.fivediamond.be.repository.OrderRepository;
import online.fivediamond.be.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
@SecurityRequirement(name="api")
public class OrderAPI {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity create(@RequestBody OrderCreationRequest request) {
        return ResponseEntity.ok(orderService.convertCartToOrder(request));
    }
}
