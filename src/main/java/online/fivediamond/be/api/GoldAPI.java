package online.fivediamond.be.api;

import online.fivediamond.be.model.gold.GoldCreationRequest;
import online.fivediamond.be.model.gold.GoldUpdateRequest;
import online.fivediamond.be.service.GoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gold")
@CrossOrigin("*")

public class GoldAPI {

    @Autowired
    GoldService goldService;

    @PostMapping
    public ResponseEntity create(GoldCreationRequest request) {
        return ResponseEntity.ok(goldService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable long id) {
        return ResponseEntity.ok(goldService.getById(id));
    }

    @GetMapping
    public ResponseEntity getAllGolds() {
        return ResponseEntity.ok(goldService.getAllGolds());
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, GoldUpdateRequest request) {
        return ResponseEntity.ok(goldService.update(id, request));
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable long id) {
        goldService.delete(id);
        return "Delete successfully";
    }
}
