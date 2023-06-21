package com.forex.controllers;

import com.forex.services.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/symbol")
public class SymbolController {
    @Autowired
    private SymbolService symbolService;

    @GetMapping
    public String all(Model model){
        model.addAttribute("symbolList",symbolService.findAll());
        return "symbol/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        model.addAttribute("symbol",symbolService.findById(id));
        return "symbol/edit";
    }
}
