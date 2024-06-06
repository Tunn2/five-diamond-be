package online.fivediamond.be.api;

import online.fivediamond.be.entity.Product;
import online.fivediamond.be.model.ProductCreationRequest;
import online.fivediamond.be.model.ProductUpdateRequest;
import online.fivediamond.be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
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

    @PostMapping
    public ResponseEntity addNewProduct(@RequestBody ProductCreationRequest request) {
        Product product = productService.createProduct(request);
        return ResponseEntity.ok(product);
    }

//    @PutMapping("{id}")
//    public ResponseEntity updateProductByID(@PathVariable long id, @RequestBody ProductUpdateRequest request) {
//        Product  product = productService.updateProductByID(id, request);
//        return ResponseEntity.ok(product);
//    }
//
    @DeleteMapping("{id}")
    public String deleteProductByID(@PathVariable long id) {
        productService.deleteProductByID(id);
        return "Delete successfully";
    }
}
