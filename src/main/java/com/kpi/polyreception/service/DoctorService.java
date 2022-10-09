package com.kpi.polyreception.service;

import com.kpi.polyreception.entity.Doctor;
import com.kpi.polyreception.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor findDoctorById(Long id) {
        return doctorRepository.getDoctors().get(id);
    }

    public List<Doctor> getAll() {
        return new ArrayList<>(doctorRepository.getDoctors().values());
    }

    public List<Doctor> getDoctorByLastName(String lastName) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doctor : doctorRepository.getDoctors().values()) {
            if (lastName.equals(doctor.getLastName())) {
                result.add(doctor);
            }
        }
        return result;
    }
}
