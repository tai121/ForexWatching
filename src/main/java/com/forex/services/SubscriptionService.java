package com.forex.services;

import com.forex.entities.Subscription;
import com.forex.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> findAll(){
        return subscriptionRepository.findAll();
    }
    public Subscription findById(Long id){
        return subscriptionRepository.findById(id).orElse(null);
    }
    public void save(Subscription subscription){
        subscriptionRepository.save(subscription);
    }
    public void cancel(Long id){
        Subscription subscription = findById(id);
        subscription.setIscanceled(true);
        save(subscription);
    }
}
