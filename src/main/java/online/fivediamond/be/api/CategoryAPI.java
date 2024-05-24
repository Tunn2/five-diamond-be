package online.fivediamond.be.api;

import lombok.Getter;
import online.fivediamond.be.entity.Category;
import online.fivediamond.be.model.CreateCategoryRequest;
import online.fivediamond.be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryAPI {
    @Autowired
    CategoryService categoryService;

    @PostMapping("create-category")
    public ResponseEntity createACategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        Category category = categoryService.create(createCategoryRequest);
        return ResponseEntity.ok(category);
    }

    @GetMapping("get-all-categories")
    public ResponseEntity getAllCategories() {
        List<Category> list = categoryService.getAllCategories();
        return ResponseEntity.ok(list);
    }
}
