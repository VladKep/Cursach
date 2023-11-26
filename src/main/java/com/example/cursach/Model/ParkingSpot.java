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
    @NotEmpty
    @Column(name = "Address")
    private String address;
    @NotEmpty
    @Column(name = "Status")
    private String status;
    @NotEmpty
    @Column(name = "Price")
    private int price;
    @OneToMany(mappedBy = "parkingSpot", cascade = CascadeType.PERSIST)
    private List<Note> notes;

    public ParkingSpot(String address, String status, int price, List<Note> notes) {
        this.address = address;
        this.status = status;
        this.price = price;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price;
    }
}
