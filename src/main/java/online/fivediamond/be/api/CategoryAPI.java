package online.fivediamond.be.api;

import lombok.Getter;
import online.fivediamond.be.entity.Category;
import online.fivediamond.be.model.CreateCategoryRequest;
import online.fivediamond.be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class CategoryAPI {
    @Autowired
    CategoryService categoryService;

    @PostMapping("category")
    public ResponseEntity createACategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        Category category = categoryService.create(createCategoryRequest);
        return ResponseEntity.ok(category);
    }

    @GetMapping("category")
    public ResponseEntity getAllCategories() {
        List<Category> list = categoryService.getAllCategories();
        return ResponseEntity.ok(list);
    }

}
