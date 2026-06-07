package kilner.jonathan.reactive_weather_api_fetcher.dto;

public record OpenWeatherResponseDataWeather(Long id,
                                             String main,
                                             String description,
                                             String icon) {
}
