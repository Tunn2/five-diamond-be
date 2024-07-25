package online.fivediamond.be.api;

import online.fivediamond.be.model.diamondPrice.DiamondPriceCreationRequest;
import online.fivediamond.be.service.DiamondPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/diamond-price")
@CrossOrigin("*")
public class DiamondPriceAPI {
    @Autowired
    DiamondPriceService diamondPriceService;

    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(diamondPriceService.get());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody DiamondPriceCreationRequest request) {
        return ResponseEntity.ok(diamondPriceService.create(request));
    }
}
