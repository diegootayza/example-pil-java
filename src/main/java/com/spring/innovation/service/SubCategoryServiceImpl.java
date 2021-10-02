/**
 * @author: Alan Benavides
 */

package com.spring.innovation.service;

import com.spring.innovation.model.SubCategory;
import com.spring.innovation.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository repository;

    public SubCategoryServiceImpl(SubCategoryRepository repository) {
        this.repository = repository;
    }

    public SubCategory save(SubCategory subCategory){
        SubCategory saved = this.repository.save(subCategory);
        return saved;
    }
}