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
    @Column(name = "StartTime")
    private LocalDateTime startTime;
    @Column(name = "EndTime")
    private LocalDateTime endTime;
    @Column(name = "FinalPrice")
    private Double finalPrice;
    @ManyToOne
    @JoinColumn(name = "Client_ID", referencedColumnName = "ID")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "ParkingSpot_ID", referencedColumnName = "ID")
    private ParkingSpot parkingSpot;

    public Note(LocalDateTime startTime, LocalDateTime endTime, Client client, ParkingSpot parkingSpot) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.finalPrice = (double) parkingSpot.getPrice() * Duration.between(startTime, endTime).toHours();
        this.client = client;
        this.parkingSpot = parkingSpot;
    }

    @Override
    public String toString() {
        return "Id=" + id +
                ", Start Time=" + startTime +
                ", End Time=" + endTime +
                ", Final Price=" + finalPrice +
                ", Client=" + client.getUsername() +
                ", Parking Spot=" + parkingSpot.getAddress();
    }
}
