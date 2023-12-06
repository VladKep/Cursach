package com.example.cursach.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Column(name = "UserName")
    private String username;
    @NotEmpty
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    @NotEmpty
    @Column(name = "Email")
    private String email;
    @NotEmpty
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Note> notes;
    @Column(name = "Role")
    private String role;

    public Client(String username, String phoneNumber, String email, String password) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserName=" + username + '\'' +
                ", phoneNumber=" + phoneNumber + '\'' +
                ", email=" + email + '\'';
    }
}
