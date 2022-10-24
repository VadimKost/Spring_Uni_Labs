package com.kpi.polyreception.controller;

import com.kpi.polyreception.entity.AppointmentTime;
import com.kpi.polyreception.entity.Role;
import com.kpi.polyreception.entity.SearchQuery;
import com.kpi.polyreception.entity.User;
import com.kpi.polyreception.repository.AppointmentTimeRepository;
import com.kpi.polyreception.service.AppointmentTimeService;
import com.kpi.polyreception.service.DoctorService;
import com.kpi.polyreception.service.ScheduleService;
import com.kpi.polyreception.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@Controller
public class MonolithController {
    private final DoctorService doctorService;
    private final AppointmentTimeService appointmentTimeService;
    private final ScheduleService scheduleService;

    private final UserService userService;
    private final AppointmentTimeRepository appointmentTimeRepository;

    public MonolithController(DoctorService doctorService, AppointmentTimeService appointmentTimeService, ScheduleService scheduleService, UserService userService, AppointmentTimeRepository appointmentTimeRepository) {
        this.doctorService = doctorService;
        this.appointmentTimeService = appointmentTimeService;
        this.scheduleService = scheduleService;
        this.userService = userService;
        this.appointmentTimeRepository = appointmentTimeRepository;
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
        String qstring = query.getQuery();
        if(qstring.matches(".*[\\\\%&].*")) {
            throw new RuntimeException("Invalid character Bitch");
        }
        model.addAttribute("doctors", doctorService.getDoctorByLastName(qstring));
        model.addAttribute("q",qstring);
        return "search-result";
    }

    @GetMapping("admin/doctor/timetable/edit/{id}")
    public String editTimeTable(Model model, @PathVariable("id") Long id,@ModelAttribute AppointmentTime time){
        model.addAttribute("doctor", doctorService.findDoctorById(id));
        model.addAttribute("timetable", scheduleService.getScheduleByDoctor(doctorService.findDoctorById(id)));
        return "timetableEdit";
    }
    @PostMapping("admin/doctor/timetable/edit/{id}")
    public String addToDoctorTimeTable(Model model, @PathVariable("id") Long id,@ModelAttribute AppointmentTime time){
        System.out.println(time);
        appointmentTimeService.createAppointmentTime(time);
        model.addAttribute("doctor", doctorService.findDoctorById(id));
        model.addAttribute("timetable", scheduleService.getScheduleByDoctor(doctorService.findDoctorById(id)));
//        System.out.println(scheduleService.getScheduleByDoctor(doctorService.findDoctorById(id)));
//        System.out.println(appointmentTimeRepository.getAppointmentTimes());
        return "timetableEdit";
    }

    @GetMapping("admin/doctor/timetable/edit/{id}/{atid}")
    public String atidDelete(Model model, @PathVariable("id") Long id,@PathVariable("atid") Long atid){
        appointmentTimeService.deleteAppointmentTime(atid);
        return "redirect:/admin/doctor/timetable/edit/"+id;
    }

    @GetMapping("admin/doctor/timetable/editAt/{id}/{atid}")
    public String atidPut(Model model, @PathVariable("id") Long id,@PathVariable("atid") Long atid,@ModelAttribute AppointmentTime time){
        AppointmentTime currentAT = appointmentTimeService.findAppointmentTimeById(atid);

        model.addAttribute("at",currentAT);
        model.addAttribute("DoctorID",id);
        return "editAT";
    }
    @PutMapping("admin/doctor/timetable/editAt/{id}/{atid}")
    public String atidGet(Model model, @PathVariable("id") Long id,@PathVariable("atid") Long atid,@ModelAttribute AppointmentTime time){
        AppointmentTime currentAT = time;
        appointmentTimeService.updateAppointmentTime(atid,currentAT);
        System.out.println(currentAT);
        System.out.println(atid);
        model.addAttribute("at",currentAT);
        model.addAttribute("did",id);
        return "editAT";
    }
}
