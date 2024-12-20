package org.example.finalproject.admin.models.admin;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private int postcode;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    private int age;

    private String role = "ADMIN";

    private String photo;

    private String imagePath;

    private char gender;

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
