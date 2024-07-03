package online.fivediamond.be.api;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.fivediamond.be.model.promotion.PromotionCreationRequest;
import online.fivediamond.be.service.PromotionService;
import online.fivediamond.be.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/promotion")
@RestController
@SecurityRequirement(name="api")
public class PromotionAPI {
    @Autowired
    PromotionService promotionService;
    @Autowired
    DateUtils dateUtils;

    @PostMapping
    public ResponseEntity create(@RequestBody PromotionCreationRequest request) {
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

    @GetMapping("/code/{code}")
    public ResponseEntity getPromotionByCode(@PathVariable String code) {
        return ResponseEntity.ok(promotionService.getPromotionByCode(code));
    }


}
