package org.example.finalproject.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.user.entities.enums.*;

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

    private Long clientNr;

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

    @Column(nullable = false)
    private String registeredBy;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private PackageEntity subscriptionPlan;

    @Column(nullable = false)
    private LocalDateTime subscriptionStartDate;

    @Column(nullable = false)
    private LocalDateTime subscriptionEndDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractStatus subscriptionStatus;


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

    @Enumerated()
    private StatusEnum status;


    @Column(nullable = false, updatable = false)
    private LocalDateTime contractStartDate;

    @Column()
    private LocalDateTime contractExpiryDate;

    @Enumerated
    @Column(nullable = false)
    private ContractStatus contractStatus;

    private String notes;

    private String declinedBy;

    private String modifiedBy;

    private LocalDateTime modifiedTime;

    private String reactivatedBy;

    private LocalDateTime reactivatedTime;


}
