package br.com.miguelalves.sw_planet_api_test_lab.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface PlanetRepository extends CrudRepository<Planet, Long>{
    Optional<Planet> findByName(String name);
}
