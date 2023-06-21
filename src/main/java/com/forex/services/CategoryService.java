package com.forex.services;

import com.forex.entities.Category;
import com.forex.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }
    public Category findByName(String name){
        return categoryRepository.findByName(name);
    }
    public List<String> listName(){
        return categoryRepository.listName();
    }

    public void save(Category category){
        categoryRepository.save(category);
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
}
