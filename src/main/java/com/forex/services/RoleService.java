package com.forex.services;

import com.forex.entities.Role;
import com.forex.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> listAll() {
        return roleRepository.findAll();
    }

    public void save(Role user) {
        roleRepository.save(user);
    }

    public Role get(long id) {
        return roleRepository.findById(id).orElse(null);
    }
    public Role getByName(String name) {
        return roleRepository.getRoleByName(name);
    }

    public void delete(long id) {
        Role role = roleRepository.findById(id).orElse(null);
        role.setIsdeleted(true);
        save(role);
    }
}
