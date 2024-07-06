package online.fivediamond.be.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.fivediamond.be.service.WarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/warranty")
@RestController
@SecurityRequirement(name="api")

public class WarrantyAPI {
    @Autowired
    WarrantyService warrantyService;

    @GetMapping
    public ResponseEntity getAllWarranty() {
        return ResponseEntity.ok(warrantyService.getAllWarranties());
    }

    @GetMapping("productId={id}")
    public ResponseEntity getByProductId(@PathVariable long id) {
        return ResponseEntity.ok(warrantyService.getByProductId(id));
    }

}
