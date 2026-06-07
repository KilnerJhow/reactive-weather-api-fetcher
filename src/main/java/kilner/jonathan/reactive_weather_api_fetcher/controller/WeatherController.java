package kilner.jonathan.reactive_weather_api_fetcher.controller;

import kilner.jonathan.reactive_weather_api_fetcher.dto.WeatherResponse;
import kilner.jonathan.reactive_weather_api_fetcher.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public Flux<WeatherResponse> getWeatherFromCityList(@RequestParam @NotEmpty(message = "cities não pode ser vazio") List<String> cities) {
        return weatherService.getWeatherForCities(cities);
    }
}
