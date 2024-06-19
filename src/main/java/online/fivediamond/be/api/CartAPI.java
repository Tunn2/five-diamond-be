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

    @PutMapping("{id}")
    public ResponseEntity addToCart(@PathVariable long id) {
        return ResponseEntity.ok(cartService.addToCart(id));
    }

    @DeleteMapping("{id}")
    public String deleteCartItemById(@PathVariable long id) {
        cartService.delete(id);
        return "Delete successfully";
    }
}
