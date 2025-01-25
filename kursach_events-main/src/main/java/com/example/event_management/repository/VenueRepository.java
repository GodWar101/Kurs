package com.example.event_management.repository;

import com.example.event_management.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    @Query("SELECT v FROM Venue v WHERE CONCAT(v.name, ' ', v.address, ' ', v.capacity) LIKE %?1%")
    List<Venue> search(String keyword);
}
