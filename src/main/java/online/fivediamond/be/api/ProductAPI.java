package online.fivediamond.be.api;

import online.fivediamond.be.entity.Product;
import online.fivediamond.be.model.product.ProductCreationRequest;
import online.fivediamond.be.model.product.ProductUpdateRequest;
import online.fivediamond.be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/product")
public class ProductAPI {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity getProductByID(@PathVariable long id) {
        return ResponseEntity.ok(productService.getProductByID(id));
    }

    @GetMapping("available")
    public ResponseEntity getAvailableProducts() {
        return ResponseEntity.ok(productService.getProductNotYetSale());
    }

    @DeleteMapping("{id}")
    public String deleteProductByID(@PathVariable long id) {
        productService.deleteProductByID(id);
        return "Delete successfully";
    }
}
