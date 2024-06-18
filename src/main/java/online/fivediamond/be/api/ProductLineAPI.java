package online.fivediamond.be.api;

import online.fivediamond.be.model.ProductLineCreationRequest;
import online.fivediamond.be.repository.ProductLineRepository;
import online.fivediamond.be.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product-line")
public class ProductLineAPI {
    @Autowired
    ProductLineService productLineService;

    @PostMapping
    public ResponseEntity create(@RequestBody ProductLineCreationRequest request) {
        return ResponseEntity.ok(productLineService.create(request));
    }
}
