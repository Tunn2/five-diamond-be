package online.fivediamond.be.api;

import online.fivediamond.be.model.sub.SubCreationRequest;
import online.fivediamond.be.model.sub.SubUpdateRequest;
import online.fivediamond.be.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sub")
@CrossOrigin("*")
public class SubAPI {

    @Autowired
    SubService subService;

    @PostMapping
    public ResponseEntity create(SubCreationRequest request) {
        return ResponseEntity.ok(subService.create(request));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable long id) {
        return ResponseEntity.ok(subService.getById(id));
    }

    @GetMapping
    public ResponseEntity getAllGolds() {
        return ResponseEntity.ok(subService.getAllSubs());
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, SubUpdateRequest request) {
        return ResponseEntity.ok(subService.update(id, request));
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable long id) {
        subService.delete(id);
        return "Delete successfully";
    }
}
