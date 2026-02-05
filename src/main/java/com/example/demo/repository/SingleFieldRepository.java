package com.example.demo.repository;

import com.example.demo.entity.SingleField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleFieldRepository extends JpaRepository<SingleField, Integer> {
    
    List<SingleField> findByFacilityFacilityId(Integer facilityId);
    
    List<SingleField> findByStatus(String status);
}
