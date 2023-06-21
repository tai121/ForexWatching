package com.forex.services;

import com.forex.entities.Packet;
import com.forex.entities.Subscription;
import com.forex.repositories.PacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacketService {
    @Autowired
    private PacketRepository packetRepository;

    public List<Packet> findAll(){
        return packetRepository.findAll();
    }
    public Packet findById(Long id){
        return packetRepository.findById(id).orElse(null);
    }
    public void save(Packet packet){
        packetRepository.save(packet);
    }
    public void cancel(Long id){
        Packet packet = findById(id);
        packet.setIsdeleted(true);
        save(packet);
    }
}
