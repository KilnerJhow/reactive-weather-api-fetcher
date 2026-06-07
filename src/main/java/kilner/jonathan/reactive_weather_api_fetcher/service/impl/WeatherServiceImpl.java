package kilner.jonathan.reactive_weather_api_fetcher.service.impl;

import kilner.jonathan.reactive_weather_api_fetcher.dto.OpenWeatherCityResponse;
import kilner.jonathan.reactive_weather_api_fetcher.dto.OpenWeatherResponse;
import kilner.jonathan.reactive_weather_api_fetcher.dto.WeatherResponse;
import kilner.jonathan.reactive_weather_api_fetcher.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WebClient webClientWeather;

    private final WebClient webClientCity;

    public WeatherServiceImpl(@Value("${openweather.weather-api-url}") String baseUrlWeather,
                              @Value("${openweather.weather-search-city}") String baseUrlCity){
        webClientWeather = WebClient.builder()
                .baseUrl(baseUrlWeather)
                .build();

        webClientCity = WebClient.builder()
                .baseUrl(baseUrlCity)
                .build();
    }

    @Override
    public Flux<WeatherResponse> getWeatherForCities(List<String> cities) {

        // do concurrent calls
        return Flux.fromArray(cities.toArray())
                .flatMap(city -> getLatLonByCityName((String) city)
                        .onErrorResume(e -> Mono.empty()))
                .flatMap(openWeatherCityResponse ->
                    webClientWeather.get()
                            .uri(uriBuilder -> uriBuilder
                                    .queryParam("lat", openWeatherCityResponse.lat())
                                    .queryParam("lon", openWeatherCityResponse.lon())
                                    .queryParam("units", "metric")
                                    .build())
                            .exchangeToMono(res -> res.bodyToMono(new ParameterizedTypeReference<OpenWeatherResponse>() {
                            }))
                            .flatMap(weatherResponse -> Mono.just(new WeatherResponse(openWeatherCityResponse.city(),
                                    weatherResponse.current().weather().getFirst().description(),
                                    weatherResponse.current().temp())))
                            .onErrorResume(e -> Mono.empty())
                )

                        ;
    }


    private Mono<OpenWeatherCityResponse> getLatLonByCityName(String city) {
        return webClientCity
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("limit", 1)
                        .queryParam("q", city)
                        .build())
                .exchangeToMono(res -> res.bodyToMono(new ParameterizedTypeReference<List<OpenWeatherCityResponse>>() {}))
                .flatMap(cityRes -> {
                    if (cityRes.isEmpty())
                        return Mono.empty();
                    return Mono.just(new OpenWeatherCityResponse(cityRes.getFirst(), city));
                })
                ;

    }
}
