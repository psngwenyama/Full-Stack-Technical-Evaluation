package com.enviro.assessment.enviro.assessment.sthembiso.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "investors")
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDate dateofBirth;

    public Investor(){

    }

    public Investor(String firstName, String lastName, String email, LocalDate dateofBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateofBirth = dateofBirth;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateofBirth() {
        return dateofBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateofBirth(LocalDate dateofBirth) {
        this.dateofBirth = dateofBirth;
    }
}
