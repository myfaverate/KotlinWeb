package edu.tyut.dating.controller

import edu.tyut.dating.bean.Person
import edu.tyut.dating.bean.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartResolver
import kotlin.random.Random


@RestController(value = "HelloController")
@RequestMapping(value = ["/hello"])
private final class HelloController {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping(value = ["/hello"])
    private final suspend fun hello(
        @RequestHeader headers: Map<String, String>,
    ): String {
        logger.info("hello -> headers: $headers")
        return "Hello World"
    }

    @GetMapping(value = ["/success"], produces = [MediaType.APPLICATION_JSON_VALUE])
    private final suspend fun success(
        @RequestHeader headers: Map<String, String>,
    ): Result<Boolean> {
        logger.info("success -> headers: $headers")
        return Result.success(message = "success", data = Random.nextBoolean())
    }

    @PostMapping(value = ["/person"])
    private final suspend fun person(
        @RequestBody person: Person,
        @RequestHeader headers: Map<String, String>,
    ): Person {
        logger.info("person -> headers: $headers, person: $person")
        return Person(name = "张书豪", age = 18, gender = "Male")
    }
}