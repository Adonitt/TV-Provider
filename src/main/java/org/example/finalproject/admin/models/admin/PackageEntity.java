package org.example.finalproject.admin.models.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.finalproject.user.entities.ClientsEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "packages")
public class PackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "pacakgeName", unique = true)
    private PackageEnum name;

    @Column(name = "pricePerMonth", nullable = false)
    private double price;

    @Column(name = "mbps", nullable = false)
    private int mbps;

    @Column(name = "nrOfChannels", nullable = false)
    private int nrOfChannels;

    @Column(name = "sportsChannels")
    private String sportsChannels; // yes or no

    @Column(name = "description1", nullable = false)
    private String description1;

    @Column(name = "description2", nullable = false)
    private String description2;


    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "channelsIncluded", nullable = false)
    private String channelsIncluded;

    @Column(name = "affordablePricing", nullable = false)
    private String affordablePricing;

    @Column(name = "hdStreaming", nullable = false)
    private String hdStreaming;

    @Column(name = "userFriendlyFeatures", nullable = false)
    private String userFriendlyFeatures;

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<ClientsEntity> clientsEntity;

}
