package com.kpi.polyreception.controller;

import com.kpi.polyreception.entity.SearchQuery;
import com.kpi.polyreception.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MonolithController {
    private final DoctorService doctorService;

    public MonolithController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public String mainPage(@ModelAttribute SearchQuery query,Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        model.addAttribute("query", new SearchQuery(""));
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
        model.addAttribute("doctors", doctorService.getAll());
        return "register";
    }

    @PostMapping("/search")
    public String searchPage(@ModelAttribute SearchQuery query, Model model) {
        model.addAttribute("doctors", doctorService.getDoctorByLastName(query.getQuery()));
        model.addAttribute("q",query.getQuery());
        return "search-result";
    }


}
