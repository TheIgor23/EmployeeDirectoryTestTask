package ru.jezemoin.emploedirectory.model.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Employee {

    private String fullName;

    private LocalDate birthday;

    private SexEnum sex;

    public int calculateAge() {
        return LocalDate.now().minusYears(birthday.getYear()).getYear();
    }

}
