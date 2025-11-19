package br.com.miguelalves.sw_planet_api_test_lab.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "planets")
public class Planet {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @NotEmpty()
    @Column(nullable = false)
    private String name;

    @NotEmpty()
    @Column(nullable = false)
    private String climate;

    @NotEmpty()
    @Column(nullable = false)
    private String terrain;

    public Planet(String climate, String terrain) {
    this.climate = climate;
    this.terrain = terrain;
  }

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }    

    public Planet() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getClimate() {
        return climate;
    }
    public void setClimate(String climate) {
        this.climate = climate;
    }
    public String getTerrain() {
        return terrain;
    }
    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Override
    public boolean equals(Object obj) {
       return EqualsBuilder.reflectionEquals(obj, this);
    }
}
