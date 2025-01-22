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

    @Column(nullable = false, unique = true)
    private PackageEnum name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int mbps;

    @Column(nullable = false)
    private int nrOfChannels;

    @Column()
    private String sportsChannels; // yes or no

    @Column(nullable = false)
    private String description1;

    @Column(nullable = false)
    private String description2;


    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private String channelsIncluded;

    @Column(nullable = false)
    private String affordablePricing;

    @Column(nullable = false)
    private String hdStreaming;

    @Column(nullable = false)
    private String userFriendlyFeatures;


}
