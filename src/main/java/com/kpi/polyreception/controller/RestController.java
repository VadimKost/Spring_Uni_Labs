package com.kpi.polyreception.controller;

import com.kpi.polyreception.entity.*;
import com.kpi.polyreception.repository.AppointmentTimeRepository;
import com.kpi.polyreception.repository.UserRepository;
import com.kpi.polyreception.service.AppointmentTimeService;
import com.kpi.polyreception.service.DoctorService;
import com.kpi.polyreception.service.ScheduleService;
import com.kpi.polyreception.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("/api")
@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final DoctorService doctorService;
    private final AppointmentTimeService appointmentTimeService;
    private final ScheduleService scheduleService;
    private final UserRepository userRepository;

    public RestController(DoctorService doctorService,
                          AppointmentTimeService appointmentTimeService,
                          ScheduleService scheduleService, UserService userService,
                          AppointmentTimeRepository appointmentTimeRepository,
                          UserRepository userRepository) {
        this.doctorService = doctorService;
        this.appointmentTimeService = appointmentTimeService;
        this.scheduleService = scheduleService;
        this.userRepository = userRepository;

        userService.saveUser(new User(1L, "admin", "admin",
                new ArrayList<>(Collections.singleton(new Role(1L, "ADMIN")))));
        userService.saveUser(new User(2L, "user", "user",
                new ArrayList<>(Collections.singleton(new Role(2L, "USER")))));
    }

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @GetMapping("/")
    public String mainPage() {
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    ResponseEntity<?> adminPage() {

        //TODO Подхватывать авторизированного юзера и проверять его права
//        if(user == null)
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        if(userRepository.findByUsername(user.getName()).getRoles().contains(new Role(1L, "ADMIN")))
//            return new ResponseEntity<>(doctorService.getAll(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(doctorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/timetable/{id}")
    ResponseEntity<?> getTimeTableById(@PathVariable("id") Long id) {
        if(scheduleService.getScheduleByDoctor(doctorService.findDoctorById(id)) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(scheduleService
                    .getScheduleByDoctor(doctorService.findDoctorById(id)),
                HttpStatus.OK);
    }


    @GetMapping("admin/{doctor}/timetable")
    ResponseEntity<?> getTimeTableByDoctor(@PathVariable("doctor") Long id){
        if(doctorService.findDoctorById(id) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(scheduleService.getScheduleByDoctor(doctorService.findDoctorById(id)) == null)
            return new ResponseEntity<>("Doctor doesn't have any timetable yet", HttpStatus.OK);
        return new ResponseEntity<>(scheduleService
                .getScheduleByDoctor(doctorService.findDoctorById(id)),
                HttpStatus.OK);
    }

    @PostMapping("admin/timetable/add")
    ResponseEntity<?> addTimeTableToDoctor(AppointmentTime time){
        appointmentTimeService.createAppointmentTime(time);
        return new ResponseEntity<String>("Good", HttpStatus.FOUND);
    }

    @PutMapping("admin/{doctor}/timetable/edit")
    ResponseEntity<?> editTimeTable(@PathVariable("doctor") String name, @ModelAttribute AppointmentTime time){
        time.setDoctorId(doctorService.getDoctorByLastName(name).get(0).getId());
        appointmentTimeService.updateAppointmentTime(time.getId(), time);

        HttpHeaders headers = new HttpHeaders();
        String redirect = "/admin/timetable";
        headers.add("Location", redirect);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("admin/timetable/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id, HttpServletResponse resp){
        appointmentTimeService.deleteAppointmentTime(id);
        resp.setStatus(200);
        return "redirect:/admin/timetable";
    }

    //TODO: Реализовать фильтрацию и пагинацию
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
}
