package com.example.cursach.Model;

import lombok.*;

import javax.persistence.*;

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
    @Column(name = "Status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "ParkingSpot_ID", referencedColumnName = "ID")
    private ParkingSpot parkingSpot;

    public Note(String startDate, String endDate, Float finalPrice, Client client, ParkingSpot parkingSpot, String status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.finalPrice = finalPrice;
        this.client = client;
        this.parkingSpot = parkingSpot;
        this.status = "Активне";
    }

    @Override
    public String toString() {
        return "Id=" + id +
                ",\nFinal Price=" + finalPrice +
                ",\nStart=" + startDate +
                ",\nEnd=" + endDate +
                ",\nParking Spot=" + parkingSpot.toStringInfo() + "\n";
    }
}
