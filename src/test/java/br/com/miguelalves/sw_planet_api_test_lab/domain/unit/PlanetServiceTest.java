package br.com.miguelalves.sw_planet_api_test_lab.domain.unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import static br.com.miguelalves.sw_planet_api_test_lab.common.PlanetConstants.INVALID_PLANET;
import static br.com.miguelalves.sw_planet_api_test_lab.common.PlanetConstants.PLANET;
import br.com.miguelalves.sw_planet_api_test_lab.domain.Planet;
import br.com.miguelalves.sw_planet_api_test_lab.domain.PlanetRepository;
import br.com.miguelalves.sw_planet_api_test_lab.domain.PlanetService;
import br.com.miguelalves.sw_planet_api_test_lab.domain.QueryBuilder;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository planetRepository;
    
    @Test
    public void createPlanet_WithValidData_ReturnsPlanet() {
        when(planetRepository.save(PLANET)).thenReturn(PLANET);
        Planet sut = planetService.create(PLANET);
        assertThat(sut).isEqualTo(PLANET);
    }

    @Test
    public void createPlanet_WithInvalidData_ThrowsException() {
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);
        assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getPlanet_WithValidId_ReturnsPlanet() {
        when(planetRepository.findById(anyLong())).thenReturn(Optional.of(PLANET));
        Optional<Planet> sut = planetService.get(1L);
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test
    public void getPlanet_ByUnexistingId_ReturnsEmpty() {
        when(planetRepository.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Planet> sut = planetService.get(1L);
        assertThat(sut).isEmpty();
    }

    @Test void getPlanet_WithValidName_ReturnsPlanet() {
        when(planetRepository.findByName(PLANET.getName())).thenReturn(Optional.of(PLANET));
        Optional<Planet> sut = planetService.getByName(PLANET.getName());
        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }

    @Test void getPlanet_WithUnexistingName_ReturnsEmpty() {
        final String name = "Unexisting Planet";
        when(planetRepository.findByName(name)).thenReturn(Optional.empty());
        Optional<Planet> sut = planetService.getByName(name);
        assertThat(sut).isEmpty();
    }

    @Test
    public void listPlanets_ReturnsAllPlanets() {
        List<Planet> planets = new ArrayList<>() {{
            add(PLANET);
        }};
        Example <Planet> query = QueryBuilder.makeQuery(new Planet(PLANET.getClimate(), PLANET.getTerrain()));
        when(planetRepository.findAll(query)).thenReturn(planets);
        List<Planet> sut = planetService.list(PLANET.getTerrain(), PLANET.getClimate());
        assertThat(sut).isNotEmpty();
        assertThat(sut).hasSize(1);
        assertThat(sut.get(0)).isEqualTo(PLANET);
    }

    @Test
    public void listPlanets_ReturnsNoPlanets() {
        when(planetRepository.findAll(any())).thenReturn(Collections.emptyList());
        List<Planet> sut = planetService.list(PLANET.getClimate(), PLANET.getTerrain());
        assertThat(sut).isEmpty();
    }
}
