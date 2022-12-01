package com.example.olympicgames.entities;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "Olympics")
public class Olympics {
    @Id
    @Column(name = "olympic_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "county_id")
    private Country country;

    @Column(name = "city")
    private String city;

    @Column(name = "year")
    private int year;

    public Olympics() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Olympics{" +
                "id='" + id + '\'' +
                '}';
    }
}
