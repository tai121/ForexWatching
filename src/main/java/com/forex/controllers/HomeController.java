package com.forex.controllers;

import com.forex.entities.Category;
import com.forex.entities.Role;
import com.forex.services.CategoryService;
import com.forex.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/")
    public String home(Model model){
//        Role roleUser = new Role();
//        roleUser.setName("USER");
//        roleService.save(roleUser);
//        model.addAttribute("categories",categoryService.listName());
        return "home/index";
    }
}
