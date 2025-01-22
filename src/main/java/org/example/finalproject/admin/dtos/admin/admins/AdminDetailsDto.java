package org.example.finalproject.admin.dtos.admin.admins;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.models.admin.AdminRole;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor

/// dto eshte info qe shkon prej controllerit ne front
// komplet te dhanat qa i ka Admin modeli, pervec password, qe nuk ia kthejme userit
public class AdminDetailsDto {
    private long id;
    private String name;
    private String surname;
    private String personalNumber;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String country;
    private String address;
    private String city;
    private int postcode;
    private String email;
    private int age;
    private AdminRole role;
    private String photo;
    private String gender;
    private String password;
    private String createdBy;

    public int calculateAge() {
        if (this.dateOfBirth != null) {
            this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
        }
        return this.age;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge();
    }

}
