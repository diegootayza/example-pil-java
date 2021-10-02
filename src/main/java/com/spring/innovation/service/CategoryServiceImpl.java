/**
 * @author: Alan Benavides
 */

package com.spring.innovation.service;

import com.spring.innovation.model.Category;
import com.spring.innovation.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category save(Category category){
        Category saved = this.repository.save(category);
        return saved;
    }
}