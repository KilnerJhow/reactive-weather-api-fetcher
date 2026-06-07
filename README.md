# Reactive Weather API Fetcher

A study project for learning **Reactive Programming** with **Spring WebFlux** and **Project Reactor**. It fetches weather data for a list of cities using the OpenWeather API in a fully non-blocking, reactive pipeline.

## Stack

- Java 25
- Spring Boot 4.0.6
- Spring WebFlux (Reactor `Flux`/`Mono`)
- WebClient
- Bean Validation

## How it works

1. The client sends a `GET /api/weather?cities=London,Paris,Tokyo` request.
2. For each city, the app queries the OpenWeather Geocoding API to resolve the city name into coordinates (`lat`/`lon`).
3. The coordinates are used to fetch the current weather from the OpenWeather One Call API.
4. All city calls are processed concurrently via `flatMap` on a `Flux`.
5. If a city is not found or a call fails, that element is skipped and the remaining cities continue processing.

## Endpoints

| Method | Path | Params | Description |
|--------|------|--------|-------------|
| GET | `/api/weather` | `cities` (list, required) | Returns weather data for each city |

## Configuration

Set the following properties in `application.properties`:

```properties
openweather.weather-api-url=<one-call-api-base-url>
openweather.weather-search-city=<geocoding-api-base-url>
```

You will need an API key from [OpenWeather](https://openweathermap.org/api) and should add it as a query parameter in the `WebClient` URI builder.

## Running

```bash
./mvnw spring-boot:run
```

## Reactive concepts practiced

- `Flux` and `Mono` as reactive types
- `flatMap` for concurrent asynchronous calls
- `switchIfEmpty` for handling not-found cases
- `onErrorResume` for per-element error isolation
- `Mono.empty()` to drop elements without breaking the stream
- `.log()` for debugging reactive pipelines
