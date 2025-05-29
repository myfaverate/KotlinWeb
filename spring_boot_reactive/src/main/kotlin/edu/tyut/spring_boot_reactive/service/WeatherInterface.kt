package edu.tyut.spring_boot_reactive.service

import edu.tyut.spring_boot_reactive.utils.Constants
import edu.tyut.spring_boot_reactive.utils.Constants.WEATHER_APP_CODE
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import reactor.core.publisher.Mono

interface WeatherInterface {
    @GetExchange(url = Constants.WEATHER_PATH, accept = [MediaType.APPLICATION_JSON_VALUE])
    fun getWeatherByAreaWI(@RequestParam(value = "area") area: String, @RequestHeader(value = "Authorization") authorization: String): Mono<String>
}