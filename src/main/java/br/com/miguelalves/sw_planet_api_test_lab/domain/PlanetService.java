package br.com.miguelalves.sw_planet_api_test_lab.domain;

import org.springframework.stereotype.Service;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }
    
    public Planet create(Planet planet) {
        return planetRepository.save(planet);
    }
}
