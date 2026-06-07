package kilner.jonathan.reactive_weather_api_fetcher.dto;

public record WeatherResponse(String city,
                              String description,
                              double temperature) {
}
