package com.forex.services;

import com.forex.entities.Symbol;
import com.forex.repositories.SymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymbolService {
    @Autowired
    private SymbolRepository symbolRepository;

    public List<Symbol> findAll(){
        return symbolRepository.findAll();
    }
    public Symbol findById(Long id){
        return symbolRepository.findById(id).orElse(null);
    }
    public List<Symbol> findSymbolWithOneCurrency(String currency){
        return symbolRepository.findSymbolWithOneCurrency(currency);
    }
    public Symbol findByName(String name){
        return symbolRepository.findByName(name);
    }
    public void save(Symbol symbol){
        symbolRepository.save(symbol);
    }
    public void delete(Long id){
        Symbol symbol = findById(id);
        symbol.setIsdeleted(true);
        save(symbol);
    }
}
