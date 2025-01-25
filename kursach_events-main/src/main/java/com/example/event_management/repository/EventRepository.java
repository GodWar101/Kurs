package com.example.event_management.repository;

import com.example.event_management.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE CONCAT(e.name, ' ', e.description, ' ', e.eventType, ' ', e.startDate, ' ', e.endDate) LIKE %?1%")
    List<Event> search(String keyword);
}
