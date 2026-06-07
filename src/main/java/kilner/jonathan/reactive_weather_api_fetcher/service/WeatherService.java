package kilner.jonathan.reactive_weather_api_fetcher.service;

import kilner.jonathan.reactive_weather_api_fetcher.dto.WeatherResponse;
import reactor.core.publisher.Flux;

import java.util.List;

public interface WeatherService {

    Flux<WeatherResponse> getWeatherForCities(List<String> cities);
}
