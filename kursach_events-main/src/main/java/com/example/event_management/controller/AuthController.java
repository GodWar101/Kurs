package com.example.event_management.controller;

import com.example.event_management.dto.UserDto;
import com.example.event_management.entity.Event;
//import com.example.event_management.entity.Session;
import com.example.event_management.entity.Venue;
import com.example.event_management.service.EventService;
//import com.example.event_management.service.SessionService;
import com.example.event_management.service.UserService;
import com.example.event_management.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;
    private final EventService eventService;
    private final VenueService venueService;
//    private final SessionService sessionService;

    // VenueService venueService, SessionService sessionService

    @Autowired
    public AuthController(UserService userService, EventService eventService, VenueService venueService) {
        this.userService = userService;
        this.eventService = eventService;
        this.venueService = venueService;
//        this.sessionService = sessionService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model) {
        if (userService.findUserByEmail(userDto.getEmail()) != null) {
            result.rejectValue("email", null, "An account with this email already exists.");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/")
    public String viewHomePage(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<Event> events = eventService.listAll(keyword);
        model.addAttribute("events", events);
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/admin_dashboard")
    public String adminDashboard(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<Event> events = eventService.listAll(keyword);
        model.addAttribute("events", events);
        model.addAttribute("keyword", keyword);
        return "admin_dashboard";
    }

    @GetMapping("/new_event")
    public String showNewEventForm(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "new_event";
    }

    @PostMapping("/save_event")
    public String saveEvent(@ModelAttribute("event") Event event) {
        eventService.save(event);
        return "redirect:/admin_dashboard";
    }

    @GetMapping("/edit_event/{id}")
    public String showEditEventForm(@PathVariable("id") Long id, Model model) {
        Event event = eventService.get(id);
        model.addAttribute("event", event);
        return "edit_event";
    }

    @GetMapping("/delete_event/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        eventService.delete(id);
        return "redirect:/admin_dashboard";
    }

    @GetMapping("/venues")
    public String viewVenueList(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<Venue> venues = venueService.listAll(keyword);
        model.addAttribute("venues", venues);
        model.addAttribute("keyword", keyword);
        return "index_ven";
    }

    @GetMapping("/venues/new_venue")
    public String showNewVenueForm(Model model) {
        Venue venue = new Venue();
        model.addAttribute("venue", venue);
        return "new_venue";
    }

    @PostMapping("/save_venue")
    public String saveVenue(@ModelAttribute("venue") Venue venue) {
        venueService.save(venue);
        return "redirect:/venues";
    }

    @GetMapping("/venues/edit_venue/{id}")
    public String showEditVenueForm(@PathVariable("id") Long id, Model model) {
        Venue venue = venueService.get(id);
        model.addAttribute("venue", venue);
        return "edit_venue";
    }

    @GetMapping("/venues/delete_venue/{id}")
    public String deleteVenue(@PathVariable("id") Long id) {
        venueService.delete(id);
        return "redirect:/venues";
    }

//    @GetMapping("/admin/sessions")
//    public String viewSessionList(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
//        List<Session> sessions = sessionService.listAll(keyword);
//        model.addAttribute("sessions", sessions);
//        model.addAttribute("keyword", keyword);
//        return "index_sl";
//    }

//    @GetMapping("/new_session")
//    public String showNewSessionForm(Model model) {
//        Session session = new Session();
//        model.addAttribute("session", session);
//        return "new_session";
//    }

//    @PostMapping("/save_session")
//    public String saveSession(@ModelAttribute("session") Session session) {
//        sessionService.save(session);
//        return "redirect:/admin/sessions";
//    }

//    @GetMapping("/edit_session/{id}")
//    public String showEditSessionForm(@PathVariable("id") Long id, Model model) {
//        Session session = sessionService.get(id);
//        model.addAttribute("session", session);
//        return "edit_session";
//    }

//    @GetMapping("/delete_session/{id}")
//    public String deleteSession(@PathVariable("id") Long id) {
//        sessionService.delete(id);
//        return "redirect:/admin/sessions";
//    }

    @GetMapping("/admin/users")
    public String viewUsers(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/index/author")
    public String author() {
        return "author";
    }

    @GetMapping("/events/description/{id}")
    public String eventDetails(@PathVariable Long id, Model model) {
        // Получение данных события по id
        Event event = eventService.get(id);
        model.addAttribute("event", event);
        return "description"; // Имя представления
    }
}
