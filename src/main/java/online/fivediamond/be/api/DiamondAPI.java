package online.fivediamond.be.api;

import online.fivediamond.be.entity.Diamond;
import online.fivediamond.be.model.DiamondCreationRequest;
import online.fivediamond.be.model.DiamondUpdateRequest;
import online.fivediamond.be.service.DiamondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/diamond")
public class DiamondAPI {

    @Autowired
    DiamondService diamondService;

    @PostMapping
    public ResponseEntity addANewDiamond(@RequestBody DiamondCreationRequest diamondCreationRequest) {
        Diamond diamond = diamondService.create(diamondCreationRequest);
        return ResponseEntity.ok(diamond);
    }

    @GetMapping
    public ResponseEntity getAllDiamonds() {
        List<Diamond> list = diamondService.getAllDiamonds();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity getDiamondByID(@PathVariable long id) {
        return ResponseEntity.ok(diamondService.getDiamondByID(id));
    }

    @PutMapping("{id}")
    public ResponseEntity updateDiamondByID(@PathVariable long id, @RequestBody DiamondUpdateRequest diamondUpdateRequest) {
        return ResponseEntity.ok(diamondService.updateDiamondByID(id, diamondUpdateRequest));
    }

    @DeleteMapping("{id}")
    public String deleteDiamondByID(@PathVariable long id) {
        diamondService.deleteDiamondByID(id);
        return "Delete successfully";
    }


}
