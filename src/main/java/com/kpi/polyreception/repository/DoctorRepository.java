package com.kpi.polyreception.repository;

import com.kpi.polyreception.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DoctorRepository {

    private Map<Long, Doctor> doctors = new HashMap<>() {
        {
            put(1L, new Doctor(1L, "Ivan", "Ivanov", "Ivanovych", "+380969966767", "ivanov@gmail.com", 1, "pediatrician"));
            put(2L, new Doctor(2L, "Petr", "Petrov", "Petrovych", "+380969966768", "petrov@gmail.com", 2, "ophthalmologist"));
            put(3L, new Doctor(3L, "Sergey", "Sergeev", "Sergeyevych", "+380969966769", "sergeev@gmail.com", 3, "dentist"));
            put(4L, new Doctor(4L, "Johny", "Sins", null, "+380969966760", "baldfrombrazzers@gmail.com", 69, "dermatologist"));
            put(5L, new Doctor(5L, "Billy", "Herringthon", null, "+380969966761", "dungeonmaster@gmail.com", 4, "otolaryngologist"));
            put(6L, new Doctor(6L, "Ivan2", "Ivanov", "Ivanovych", "+380969966767", "ivanov@gmail.com", 1, "pediatrician"));
        }
    };

    public Map<Long, Doctor> getDoctors() {
        return doctors;
    }

}
