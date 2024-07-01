package online.fivediamond.be.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.fivediamond.be.model.promotion.PromotionCreationRequest;
import online.fivediamond.be.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RequestMapping("api/promotion")
@RestController
@SecurityRequirement(name="api")
public class PromotionAPI {
    @Autowired
    PromotionService promotionService;


    @PostMapping
    public ResponseEntity create(PromotionCreationRequest request) {
        return ResponseEntity.ok(promotionService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity getPromotionById(@PathVariable long id) {
        return ResponseEntity.ok(promotionService.getPromotionById(id));
    }

    @GetMapping
    public ResponseEntity getAllPromotion() {
        return ResponseEntity.ok(promotionService.getAllPromotion());
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(promotionService.deletePromotionById(id));
    }

    @GetMapping("/code")
    public ResponseEntity getPromotionByCode(@RequestBody String code) throws ParseException {
        return ResponseEntity.ok(promotionService.getPromotionByCode(code));
    }

}
