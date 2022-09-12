package com.kpi.lab1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @GetMapping("/")
    String index() {
        return "index.html";
    }

    @GetMapping("/about")
    String about() {
        return "about.html";
    }
}
