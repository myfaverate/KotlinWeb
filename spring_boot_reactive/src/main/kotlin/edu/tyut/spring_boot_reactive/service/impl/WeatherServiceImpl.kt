package edu.tyut.spring_boot_reactive.service.impl

import edu.tyut.spring_boot_reactive.service.WeatherInterface
import edu.tyut.spring_boot_reactive.service.WeatherService
import edu.tyut.spring_boot_reactive.utils.Constants
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.codec.ClientCodecConfigurer
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Mono

@Service
class WeatherServiceImpl : WeatherService {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun getWeatherByArea(area: String): Mono<String> {
        // 1. WebClient
        // return WebClient.create(Constants.WEATHER_BASE_URL)
        //     .get()
        //     .uri{ uriBuilder: UriBuilder -> uriBuilder.queryParam("area", area).path(Constants.WEATHER_PATH).build() }
        //     .accept(MediaType.APPLICATION_JSON)
        //     .header("Authorization", "APPCODE ${Constants.WEATHER_APP_CODE}")
        //     .retrieve()
        //     .bodyToMono(String::class.java)

        // 2 WebClient
        logger.info("Retrieving weather for $area")
        val webClient: WebClient = WebClient.builder().baseUrl(Constants.WEATHER_BASE_URL)
            .codecs { clientCodecConfigurer: ClientCodecConfigurer -> clientCodecConfigurer.defaultCodecs().maxInMemorySize(256 * 1024 * 1024) }
            .build()
        val factory: HttpServiceProxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build()
        val weatherInterface: WeatherInterface = factory.createClient<WeatherInterface>(WeatherInterface::class.java)
        return weatherInterface.getWeatherByAreaWI(area = area, authorization = "APPCODE ${Constants.WEATHER_APP_CODE}")
    }
}