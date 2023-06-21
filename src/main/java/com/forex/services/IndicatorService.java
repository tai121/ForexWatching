package com.forex.services;

import com.forex.entities.Indicator;
import com.forex.repositories.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicatorService {
    @Autowired
    private IndicatorRepository indicatorRepository;

    public List<Indicator> findAll(){
        return indicatorRepository.findAll();
    }
    public Indicator findById(Long id){
        return indicatorRepository.findById(id).orElse(null);
    }
    public void save(Indicator indicator){
        indicatorRepository.save(indicator);
    }
    public void cancel(Long id){
        Indicator indicator = findById(id);
        indicator.setIsdeleted(true);
        save(indicator);
    }
}
