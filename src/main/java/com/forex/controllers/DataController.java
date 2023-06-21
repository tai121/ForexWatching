package com.forex.controllers;

import com.forex.entities.Category;
import com.forex.entities.HistoricalData;
import com.forex.services.CategoryService;
import com.forex.services.HistoricalDataService;
import com.forex.services.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ta4j.core.*;
import org.ta4j.core.indicators.*;
import org.ta4j.core.indicators.helpers.*;
import org.ta4j.core.num.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    private HistoricalDataService historicalDataServices;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SymbolService symbolService;
    @GetMapping
    public String home(){
        return "data/index";
    }
    @GetMapping("listsymbol/{category}")
    public String listSymbol(@PathVariable("category") String category, Model model){
        model.addAttribute("symbols",categoryService.findByName(category).getListSymbol());
        return "data/listsymbol";
    }

    @GetMapping("symbols/{symbol}")
    public String dataSymbol(@PathVariable("symbol") String symbol,Model model){
        model.addAttribute("symbol",symbol);
        List<HistoricalData> historicalDataList = symbolService.findByName(symbol).getForex_data();
        model.addAttribute("data",historicalDataList);

        return "data/details";
    }
}
