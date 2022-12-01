package com.example.olympicgames.entities;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "Countries")
public class Country {
    @Id
    @Column(name = "country_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "population")
    private int population;

    public Country() { }

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

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id='" + id + '\'' +
                '}';
    }
}
