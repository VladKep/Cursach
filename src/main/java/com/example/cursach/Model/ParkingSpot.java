package com.example.cursach.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ParkingSpot {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Address")
    private String address;
    @Column(name = "Status")
    private String status;
    @Column(name = "Price")
    private int price;
    @Column(name = "SpotNumber")
    private int spoNumber;
    @OneToMany(mappedBy = "parkingSpot", cascade = CascadeType.PERSIST)
    private List<Note> notes;

    public ParkingSpot(String address, String status, int price, int spoNumber) {
        this.address = address;
        this.status = status;
        this.price = price;
        this.spoNumber = spoNumber;
    }

    @Override
    public String toString() {
        return "address='" + address + '\'' +
                ",number='" + spoNumber + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price;
    }

    public String toStringInfo() {
        return "address='" + address + '\'' +
                ",number='" + spoNumber + '\'';
    }
}
