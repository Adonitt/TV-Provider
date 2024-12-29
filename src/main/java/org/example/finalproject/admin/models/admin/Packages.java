package org.example.finalproject.admin.models.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "packages")
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "pacakgeName", unique = true)
    private String name;

    @Column(name = "pricePerMonth", nullable = false)
    private double price;

    @Column(name = "mbps", nullable = false)
    private int mbps;

    @Column(name = "nrOfChannels", nullable = false)
    private int nrOfChannels;

    @Column(name = "sportsChannels")
    private String sportsChannels; // yes or no

}
