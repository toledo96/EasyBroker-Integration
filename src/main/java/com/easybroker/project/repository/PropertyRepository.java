package com.easybroker.project.repository;

import com.easybroker.project.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Long> {
    List<Property> findByLocationContainingIgnoreCase(String location);
    List<Property> findByPriceLessThan(Double price);
}
