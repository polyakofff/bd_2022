package com.example.olympicgames.entities;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(schema = "public", name = "Players")
public class Player {
    @Id
    @Column(name = "player_id")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "birthdate")
    private LocalDate birthday;

    public Player() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                '}';
    }
}
