package online.fivediamond.be.api;

import online.fivediamond.be.model.collection.CollectionCreationRequest;
import online.fivediamond.be.model.collection.CollectionUpdateRequest;
import online.fivediamond.be.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/collection")
public class CollectionAPI {
    @Autowired
    CollectionService collectionService;

    @PostMapping
    public ResponseEntity create(@RequestBody CollectionCreationRequest request) {
        return ResponseEntity.ok(collectionService.create(request));
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody CollectionUpdateRequest request) {
        return  ResponseEntity.ok(collectionService.update(id, request));
    }

    @GetMapping
    public ResponseEntity getAllCollections() {
        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable long id) {
        return ResponseEntity.ok(collectionService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(collectionService.delete(id));
    }

    @GetMapping("available")
    public ResponseEntity getAvailableCollections() {
        return ResponseEntity.ok(collectionService.getAvailableCollections());
    }

}
