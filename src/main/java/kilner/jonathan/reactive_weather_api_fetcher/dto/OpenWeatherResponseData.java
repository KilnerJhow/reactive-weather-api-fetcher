package kilner.jonathan.reactive_weather_api_fetcher.dto;

import java.sql.Timestamp;
import java.util.List;

public record OpenWeatherResponseData(Timestamp dt,
                                      Timestamp sunrise,
                                      Timestamp sunset,
                                      Double temp,
                                      Double feelsLike,
                                      Long pressure,
                                      Integer humidity,
                                      Double dewPoint,
                                      Double uvi,
                                      Long clouds,
                                      Long visibility,
                                      Double windSpeed,
                                      Long windDeg,
                                      List<OpenWeatherResponseDataWeather> weather) {
}
