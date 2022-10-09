package com.kpi.polyreception.controller;

import com.kpi.polyreception.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
    private final DoctorService doctorService;

    public FrontController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("doctors", doctorService.getAll());
        return "index";
    }
}
