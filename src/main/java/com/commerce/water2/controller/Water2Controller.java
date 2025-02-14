package com.commerce.water2.controller;

import com.commerce.water2.model.Water2;
import com.commerce.water2.service.Water2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/water2")
public class Water2Controller {

    private final Water2Service water2Service;

    @Autowired
    public Water2Controller(Water2Service water2Service) {
        this.water2Service = water2Service;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping
    public ResponseEntity<List<Water2>> getAll() {
        List<Water2> waterList = water2Service.getAll();
        return ResponseEntity.ok(waterList);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/{id}")
    public ResponseEntity<Water2> getById(@PathVariable Long id) {
        Optional<Water2> water2 = water2Service.getById(id);
        return water2.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping("/save")
    public ResponseEntity<Water2> create(@RequestBody Water2 water2) {
        Water2 createdWater2 = water2Service.create(water2);
        return ResponseEntity.ok(createdWater2);
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @PutMapping("/{id}")
    public ResponseEntity<Water2> update(@PathVariable Long id, @RequestBody Water2 water2) {
        Water2 updatedWater2 = water2Service.update(id, water2);
        return updatedWater2 != null ? ResponseEntity.ok(updatedWater2) : ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (water2Service.getById(id).isPresent()) {
            water2Service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
