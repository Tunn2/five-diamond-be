package online.fivediamond.be.api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import online.fivediamond.be.entity.Material;
import online.fivediamond.be.model.MaterialCreationRequest;
import online.fivediamond.be.model.MaterialUpdateRequest;
import online.fivediamond.be.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/material")
public class MaterialAPI {

    @Autowired
    MaterialService materialService;

    @PostMapping
    public ResponseEntity create(@RequestBody MaterialCreationRequest request) {
        return ResponseEntity.ok(materialService.create(request));
    }

    @GetMapping
    public ResponseEntity getAllMaterial() {
        return ResponseEntity.ok(materialService.getAllMaterials());
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody MaterialUpdateRequest request) {
        return ResponseEntity.ok(materialService.update(id, request));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable long id) {
        materialService.delete(id);
        return "Delete successfully";
    }

    @GetMapping("{id}")
    public ResponseEntity getByID(@PathVariable long id) {
        Material material = materialService.getMaterialByID(id);
        return ResponseEntity.ok(material);
    }

}
