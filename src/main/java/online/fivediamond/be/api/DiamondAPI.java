package online.fivediamond.be.api;

import online.fivediamond.be.entity.Diamond;
import online.fivediamond.be.model.diamond.DiamondCreationRequest;
import online.fivediamond.be.model.diamond.DiamondUpdateRequest;
import online.fivediamond.be.service.DiamondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/diamond")
@CrossOrigin("*")

public class DiamondAPI {

    @Autowired
    DiamondService diamondService;

    @PostMapping
    public ResponseEntity create(@RequestBody DiamondCreationRequest request) {
        return ResponseEntity.ok(diamondService.create(request));
    }

    @GetMapping
    public ResponseEntity getAllDiamonds() {
        return ResponseEntity.ok(diamondService.getAllDiamonds());
    }
    

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody DiamondUpdateRequest request) {
        return ResponseEntity.ok(diamondService.update(id, request));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable long id) {
        diamondService.delete(id);
        return "Delete successfully";
    }

    @GetMapping("{id}")
    public ResponseEntity getByID(@PathVariable long id) {
        Diamond diamond = diamondService.getDiamondByID(id);
        return ResponseEntity.ok(diamond);
    }



    @GetMapping("search")
    public ResponseEntity getDiamonds(@RequestParam String shape,@RequestParam String origin, @RequestParam double size, @RequestParam double carat, @RequestParam String color, @RequestParam String cut, @RequestParam String clarity) {
        return ResponseEntity.ok(diamondService.getDiamonds(shape, origin, size, carat, color, cut, clarity));
    }

    @GetMapping("update")
    public ResponseEntity getDiamonds1(@RequestParam String shape,@RequestParam String origin, @RequestParam double size, @RequestParam double carat, @RequestParam String color, @RequestParam String cut, @RequestParam String clarity) {
        return ResponseEntity.ok(diamondService.getDiamonds(shape, origin, size, carat, color, cut, clarity));
    }

}
