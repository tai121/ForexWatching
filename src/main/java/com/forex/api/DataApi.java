package com.forex.api;

import com.forex.entities.HData;
import com.forex.entities.HistoricalData;
import com.forex.services.HistoricalDataService;
import com.forex.services.SymbolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class DataApi {
    @Autowired
    private HistoricalDataService historicalDataService;
    @Autowired
    private SymbolService symbolService;

    @GetMapping("/symbols/{symbol}")
    public List<HData> hisdata(@PathVariable("symbol") String symbol){
        List<HData> list = new ArrayList<>();
        List<HistoricalData> l = symbolService.findByName(symbol).getForex_data();
        for(HistoricalData d : l){
            HData h = new HData(d);
            list.add(h);
        }
        return list;
    }

}
