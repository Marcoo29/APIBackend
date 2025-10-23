package com.uade.tpo.ecommerce.service.impl;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.ecommerce.exceptions.CategoryDuplicateException;
import com.uade.tpo.ecommerce.repository.CategoryRepository;
import com.uade.tpo.ecommerce.service.inter.CategoryService;
import com.uade.tpo.ecommerce.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> getCategories(PageRequest pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public Category createCategory(String description) {
        List<Category> categories = categoryRepository.findByDescription(description);
        if (categories.isEmpty())
            return categoryRepository.save(new Category(description));
        throw new CategoryDuplicateException();
    }

    public Category updateCategory(Long id, String newDescription) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        List<Category> duplicates = categoryRepository.findByDescription(newDescription);
        boolean existsDuplicate = duplicates.stream()
                .anyMatch(c -> !c.getId().equals(id));

        if (existsDuplicate) {
            throw new CategoryDuplicateException();
        }

        category.setDescription(newDescription);
        return categoryRepository.save(category);
    }


}