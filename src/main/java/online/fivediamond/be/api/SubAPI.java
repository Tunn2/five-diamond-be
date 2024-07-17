package online.fivediamond.be.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.fivediamond.be.model.sub.SubCreationRequest;
import online.fivediamond.be.model.sub.SubUpdateRequest;
import online.fivediamond.be.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sub")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class SubAPI {

    @Autowired
    SubService subService;

    @PostMapping
    public ResponseEntity create(@RequestBody SubCreationRequest request) {
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
    public ResponseEntity update(@PathVariable long id,@RequestBody SubUpdateRequest request) {
        return ResponseEntity.ok(subService.update(id, request));
    }

    @DeleteMapping("{id}")
    public String deleteById(@PathVariable long id) {
        subService.delete(id);
        return "Delete successfully";
    }
}
