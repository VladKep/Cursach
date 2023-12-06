package com.example.cursach.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Note {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "StartDate")
    private String startDate;
    @Column(name = "EndDate")
    private String endDate;
    @Column(name = "FinalPrice")
    private Float finalPrice;
    @ManyToOne
    @JoinColumn(name = "Client_ID", referencedColumnName = "ID")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "ParkingSpot_ID", referencedColumnName = "ID")
    private ParkingSpot parkingSpot;

    public Note(String startDate, String endDate, Float finalPrice, Client client, ParkingSpot parkingSpot) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.finalPrice = finalPrice;
        this.client = client;
        this.parkingSpot = parkingSpot;
    }

    @Override
    public String toString() {
        return "Id=" + id +
                ", Final Price=" + finalPrice +
                ", Start=" + startDate +
                ", End=" + endDate +
                ", Parking Spot=" + parkingSpot.toStringInfo();
    }
}
