package org.example.finalproject.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.PreferredLanguages;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "clients")
public class ClientsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketNr;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    private Cities city;

    @Column(nullable = false)
    private String address;

    @Column
    private LocalDateTime requestTime = LocalDateTime.now();

    @OneToOne
    private PackageEntity subscriptionPlan;

    @Column(nullable = false)
    private String accountId;

    @Column(nullable = false)
    private boolean subscriptionActive;

    @Column(nullable = false)
    private String billingAddress;

    @Column
    private LocalDateTime installationDate;

    @Column
    private PreferredLanguages preferredLanguage;

    @Column
    private String deviceType;

}
