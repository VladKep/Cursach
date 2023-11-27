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
    @Column(name = "UserName")
    private String userName;
    @NotEmpty
    @UniqueElements
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @Email
    @UniqueElements
    @NotEmpty
    @Column(name = "Email")
    private String email;
    @NotEmpty
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    private List<Note> notes;

    public Client(String userName, String phoneNumber, String email, String password) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserName=" + userName + '\'' +
                ", phoneNumber=" + phoneNumber + '\'' +
                ", email=" + email + '\'';
    }
}
