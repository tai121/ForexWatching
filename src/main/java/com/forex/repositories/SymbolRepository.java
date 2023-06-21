package com.forex.repositories;

import com.forex.entities.HistoricalData;
import com.forex.entities.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SymbolRepository extends JpaRepository<Symbol,Long> {
    @Query("select s from Symbol s where concat(s.first_currency,s.second_currency) LIKE %?1%")
    public List<Symbol> findSymbolWithOneCurrency(String currency);
    @Query("select s from Symbol s where concat(s.first_currency,s.second_currency) = ?1")
    public Symbol findByName(String name);
}
