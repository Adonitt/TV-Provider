package org.example.finalproject.user.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.StatusEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "clientsReq")
public class ClientRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ticketNr;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated
    private Cities city;

    @Column(nullable = false)
    private String address;

    @Column
    private LocalDateTime requestTime = LocalDateTime.now();

    @Enumerated
    private StatusEnum status;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private PackageEntity subscriptionPlan;

    private String declinedBy;

}
