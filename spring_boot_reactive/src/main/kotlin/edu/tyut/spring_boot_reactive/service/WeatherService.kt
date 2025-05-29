package edu.tyut.spring_boot_reactive.service

import org.springframework.web.service.annotation.GetExchange
import reactor.core.publisher.Mono

interface WeatherService {
    fun getWeatherByArea(area: String): Mono<String>
}