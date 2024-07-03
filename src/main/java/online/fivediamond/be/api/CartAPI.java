package online.fivediamond.be.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.fivediamond.be.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
@SecurityRequirement(name = "api")
public class CartAPI {
    @Autowired
    CartService cartService;

    @PostMapping("{id}")
    public ResponseEntity addToCart(@PathVariable long id) {
        return ResponseEntity.ok(cartService.addToCart(id));
    }

    @GetMapping
    public ResponseEntity getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    @DeleteMapping("{id}")
    public String deleteCartItemById(@PathVariable long id) {
        cartService.delete(id);
        return "Delete successfully";
    }

    @GetMapping("check")
    public ResponseEntity checkQuantity() {
        String check = cartService.checkQuantity();
        return ResponseEntity.ok(check);
    }

    @PutMapping("add/{id}")
    public ResponseEntity addCartItem(@PathVariable long id) {
        return ResponseEntity.ok(cartService.add(id));
    }

    @PutMapping("remove/{id}")
    public ResponseEntity updateQuantityCartItem(@PathVariable long id) {
        return ResponseEntity.ok(cartService.removeOneProduct(id));
    }
}
