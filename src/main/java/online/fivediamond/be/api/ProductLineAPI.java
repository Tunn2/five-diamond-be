package online.fivediamond.be.api;

import online.fivediamond.be.model.productLine.ProductLineCreationRequest;
import online.fivediamond.be.model.productLine.ProductLineUpdateRequest;
import online.fivediamond.be.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product-line")
public class ProductLineAPI {
    @Autowired
    ProductLineService productLineService;

    @PostMapping
    public ResponseEntity create(@RequestBody ProductLineCreationRequest request) {
        return ResponseEntity.ok(productLineService.create(request));
    }

    @GetMapping("available")
    public ResponseEntity getAvailableProductLines() {
        return ResponseEntity.ok(productLineService.getAvailableProductLine());
    }

    @GetMapping
    public ResponseEntity getAllProductLines() {
        return ResponseEntity.ok(productLineService.getAllProductLines());
    }

    @PutMapping("{id}")
    public ResponseEntity updateById(@PathVariable long id, ProductLineUpdateRequest request) {
        return ResponseEntity.ok(productLineService.update(id, request));
    }
}
