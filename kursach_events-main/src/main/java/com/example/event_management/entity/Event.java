package com.example.event_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String eventType; // Тип мероприятия
    private LocalDate startDate;
    private LocalDate endDate;
    private String organizer;
    private String location; // Локация мероприятия
    // Сеттер
    private String eventPoster;

    public Event() {}

}
