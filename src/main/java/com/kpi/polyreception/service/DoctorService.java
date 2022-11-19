package com.kpi.polyreception.service;

import com.kpi.polyreception.entity.Doctor;
import com.kpi.polyreception.repository.DoctorRepository;
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
        for (Doctor doctor : doctorRepository.getDoctors()) {
            if (id.equals(doctor.getId())) {
                return doctor;
            }
        }

        return null;
    }

    public List<Doctor> getAll() {
        return doctorRepository.getDoctors();
    }

    public List<Doctor> getDoctorByLastName(String lastName) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doctor : doctorRepository.getDoctors()) {
            if (lastName.equals(doctor.getLastName())) {
                result.add(doctor);
            }
        }

        return result.size() > 0 ? result : null;
    }
}
