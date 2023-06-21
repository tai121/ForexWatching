package com.forex.services;

import com.forex.entities.HistoricalData;
import com.forex.repositories.HistoricalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricalDataService {
    @Autowired
    private HistoricalDataRepository historicalDataRepository;

    public List<HistoricalData> findAll(){
        return historicalDataRepository.findAll();
    }
    public HistoricalData findById(Long id){
        return historicalDataRepository.findById(id).orElse(null);
    }
    public List<HistoricalData> findBySymbol(String symbol){
        return historicalDataRepository.findBySymbol(symbol);
    }
    public void save(HistoricalData historicalData){
        historicalDataRepository.save(historicalData);
    }
    public void delelte(Long id){
        HistoricalData historicalData = findById(id);
        historicalData.setIsdeleted(true);
        save(historicalData);
    }
}
