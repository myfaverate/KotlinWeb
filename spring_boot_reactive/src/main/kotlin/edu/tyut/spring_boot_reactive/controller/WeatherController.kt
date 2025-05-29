package edu.tyut.spring_boot_reactive.controller

import edu.tyut.spring_boot_reactive.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController(value = "/weather")
class WeatherController(
    private val weatherService: WeatherService
){
    @GetMapping("/hello")
    fun hello(): String = "Hello World 世界!"
    @GetMapping("/area")
    fun getWeatherByCity(@RequestParam(value = "area") area: String): Mono<String> {
        return weatherService.getWeatherByArea(area = area)
    }
}