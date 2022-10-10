package com.kpi.polyreception.controller;

import com.kpi.polyreception.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MonolithController {
    private final DoctorService doctorService;

    public MonolithController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
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

    @GetMapping("/acc")
    public String myAccountPage(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "my-account";
    }

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "register";
    }

    @GetMapping("/")
    public String searchPage(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "search-result";
    }


}
