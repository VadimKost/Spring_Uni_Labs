package com.kpi.polyreception.controller;

import com.kpi.polyreception.entity.Role;
import com.kpi.polyreception.entity.SearchQuery;
import com.kpi.polyreception.entity.User;
import com.kpi.polyreception.service.DoctorService;
import com.kpi.polyreception.service.ScheduleService;
import com.kpi.polyreception.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;

@Controller
public class MonolithController {
    private final DoctorService doctorService;
    private final ScheduleService scheduleService;

    private final UserService userService;

    public MonolithController(DoctorService doctorService, ScheduleService scheduleService, UserService userService) {
        this.doctorService = doctorService;
        this.scheduleService = scheduleService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String mainPage(@ModelAttribute SearchQuery query,Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("query", new SearchQuery(""));

        userService.saveUser(new User(1L, "admin", "admin",
                new ArrayList<>(Collections.singleton(new Role(1L, "ADMIN")))));
        userService.saveUser(new User(2L, "user", "user",
                new ArrayList<>(Collections.singleton(new Role(2L, "USER")))));
        return "index";
    }
    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "admin-panel";
    }
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "login";
    }

    @GetMapping("/account")
    public String myAccountPage(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "my-account";
    }

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (!userService.saveUser(user)){
            model.addAttribute("error", "User with given login is already present");
            return "register";
        }

        return "redirect:/";
    }

    @GetMapping("/timetable/{id}")
    public String timeTablePage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("doctor", doctorService.findDoctorById(id));
        model.addAttribute("timetable", scheduleService.getScheduleByDoctor(doctorService.findDoctorById(id)));
        return "timetable";
    }

    @PostMapping("/search")
    public String searchPage(@ModelAttribute SearchQuery query, Model model) {
        model.addAttribute("doctors", doctorService.getDoctorByLastName(query.getQuery()));
        model.addAttribute("q",query.getQuery());
        return "search-result";
    }


}
