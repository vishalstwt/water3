package com.commerce.water2.service;

import com.commerce.water2.model.Water2;
import com.commerce.water2.repository.Water2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Water2Service {

    private final Water2Repository water2Repository;

    @Autowired
    public Water2Service(Water2Repository water2Repository) {
        this.water2Repository = water2Repository;
    }

    public List<Water2> getAll() {
        return water2Repository.findAll();
    }

    public Optional<Water2> getById(Long id) {
        return water2Repository.findById(id);
    }

    public Water2 create(Water2 water2) {
        return water2Repository.save(water2);
    }

    public Water2 update(Long id, Water2 updatedWater2) {
        return water2Repository.findById(id).map(water2 -> {
            water2.setName(updatedWater2.getName());
            water2.setAddress(updatedWater2.getAddress());
            water2.setPhoneNo(updatedWater2.getPhoneNo());
            water2.setAdditionalInfo(updatedWater2.getAdditionalInfo());
            return water2Repository.save(water2);
        }).orElse(null);
    }

    public void delete(Long id) {
        water2Repository.deleteById(id);
    }
}
