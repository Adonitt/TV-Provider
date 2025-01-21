package org.example.finalproject.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.DevicesTypes;
import org.example.finalproject.user.entities.enums.PreferredLanguages;
import org.example.finalproject.user.entities.enums.StatusEnum;

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

    @Column(nullable = false)
    private LocalDateTime requestTime = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime registeredTIme = LocalDateTime.now();

    //    @Column(nullable = false)
    private String registeredBy;

    @Enumerated
    private PackageEnum subscriptionPlan;

    @Column(nullable = false)
    private boolean subscriptionActive;

    @Column(nullable = false)
    private String billingAddress;

    @Column(nullable = false)
    private LocalDateTime installationDate;

    @Enumerated
    @Column(nullable = false)
    private PreferredLanguages preferredLanguage;

    @Enumerated
    @Column(nullable = false)
    private DevicesTypes deviceType;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

}
