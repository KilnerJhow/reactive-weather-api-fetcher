package kilner.jonathan.reactive_weather_api_fetcher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherCityResponse(String name,
                                      Double lat,
                                      Double lon,
                                      String country,
                                      String state,
                                      String city) {

    public OpenWeatherCityResponse(OpenWeatherCityResponse first, String city) {
        this(first.name(), first.lat(), first.lon(), first.country(), first.state(), city);
    }

    public OpenWeatherCityResponse(String city) {
        this(null, null, null, null, null, city);
    }
}
