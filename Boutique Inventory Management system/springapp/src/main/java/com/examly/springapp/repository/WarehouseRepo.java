package com.examly.springapp.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examly.springapp.model.Warehouse;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {

    @Query("SELECT w FROM Warehouse w WHERE w.location = :location")
    List<Warehouse> findByLocation(String location);

    @Query("SELECT w FROM Warehouse w WHERE w.location = :location AND w.name = :name")
    List<Warehouse> findByLocationAndName(String location, String name);
}

