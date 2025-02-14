package com.commerce.water2.repository;

import com.commerce.water2.model.Water2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Water2Repository extends JpaRepository<Water2, Long> {
}
