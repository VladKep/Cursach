package com.example.cursach.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @UniqueElements
    @Column(name = "FirstName")
    private String firstName;
    @NotEmpty
    @UniqueElements
    @Column(name = "SecondName")
    private String secondName;
    @NotEmpty
    @UniqueElements
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Email
    @UniqueElements
    @NotEmpty
    @Column(name = "Email")
    private String email;
    @OneToMany(mappedBy = "client")
    private List<Note> notes;

    public Client(String firstName, String secondName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
