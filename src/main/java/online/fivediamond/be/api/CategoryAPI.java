package online.fivediamond.be.api;

import online.fivediamond.be.entity.Category;
import online.fivediamond.be.model.category.CategoryCreationRequest;
import online.fivediamond.be.model.category.CategoryUpdateRequest;
import online.fivediamond.be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
@CrossOrigin("*")

public class CategoryAPI {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity createNewCategory(@RequestBody CategoryCreationRequest request) {
        return ResponseEntity.ok(categoryService.create(request));
    }

    @GetMapping
    public ResponseEntity getAllCategory() {
        List<Category> list = categoryService.getAllCategories();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("{id}")
    public String deleteCategoryByID(@PathVariable long id){
        categoryService.deleteCategoryByID(id);
        return "Deleted successfully";
    }

    @PutMapping("{id}")
    public ResponseEntity updateCategoryByID(@PathVariable long id, @RequestBody CategoryUpdateRequest request) {
        return ResponseEntity.ok(categoryService.updateCategoryByID(id, request));
    }

}
