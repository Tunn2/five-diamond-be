package online.fivediamond.be.service;

import online.fivediamond.be.entity.Category;
import online.fivediamond.be.model.category.CategoryCreationRequest;
import online.fivediamond.be.model.category.CategoryUpdateRequest;
import online.fivediamond.be.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category create(CategoryCreationRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findCategoriesByIsDeletedFalse();
    }

    Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    public void deleteCategoryByID(long id) {
        Category category = getCategoryByID(id);
        category.setDeleted(true);
        categoryRepository.save(category);
    }

    public Category updateCategoryByID(long id, CategoryUpdateRequest request) {
        Category category = getCategoryByID(id);
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    public Category getCategoryByID(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
