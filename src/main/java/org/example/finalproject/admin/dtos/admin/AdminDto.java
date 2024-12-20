package org.example.finalproject.admin.dtos.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

/// dto eshte info qe shkon prej controllerit ne front
// komplet te dhanat qa i ka Admin modeli, pervec password, qe nuk ia kthejme userit
public class AdminDto {
    private int id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String country;
    private String address;
    private String city;
    private int postcode;
    private String email;
    private int age;
    private String role = "ADMIN";
    private String photo;
    private String imagePath;
    private char gender;

}
