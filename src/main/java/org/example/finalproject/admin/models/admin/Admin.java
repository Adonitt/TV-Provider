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

@Entity(name = "simple-admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    @PositiveOrZero(message = "ID must be a positive number")
    private long id;

    @Column(nullable = false)
    @Size(min = 2, max = 50, message = "Name must be at least 2 characters long and at most 50 characters long")
    @NotBlank(message = "Name is required")
    private String name;


    @Column(nullable = false)
    @Size(min = 2, max = 50, message = "Name must be at least 2 characters long and at most 50 characters long")
    @NotBlank(message = "Name is required")
    private String surname;

    @Column(nullable = false)
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @Size(min = 9, max = 25, message = "Phone number must be at least 9 characters long and at most 25 characters long")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;


    @Column(nullable = false)
    @NotBlank(message = "Country is required")
    private String country;


    @Column(nullable = false)
    @NotBlank(message = "Address is required")
    private String address;

    @Column(nullable = false)
    @NotBlank(message = "City is required")
    private String city;

    @Column(nullable = false)
    private int postcode;

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one number")
    private String password;

    private int age;

    private String role = "Super Admin";

    private String photo;

    private String imagePath;

    private char gender;

    public int calculateAge() {
        if (this.dateOfBirth != null) {
            this.age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();
        }
        return this.age;  // Return the calculated age
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.age = calculateAge();  // Automatically calculate and set the age
    }


}
