package br.com.miguelalves.sw_planet_api_test_lab.common;

import br.com.miguelalves.sw_planet_api_test_lab.domain.Planet;

public class PlanetConstants {
    public static final Planet PLANET = new Planet("name", "climate", "terrain");
    public static final Planet INVALID_PLANET = new Planet("", "", "");
}
