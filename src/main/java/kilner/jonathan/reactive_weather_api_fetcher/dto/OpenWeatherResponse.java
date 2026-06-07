package kilner.jonathan.reactive_weather_api_fetcher.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherResponse(Double lat,
                                  Double lon,
                                  String timezone,
                                  Long timezoneOffset,
                                  OpenWeatherResponseData current) {

}
