package edu.tyut.dating.controller

import edu.tyut.dating.bean.Person
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController(value = "HelloController")
@RequestMapping(value = ["/hello"])
internal class HelloController {

    private final val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping(value = ["/hello"])
    internal suspend fun hello(
        @RequestHeader headers: Map<String, String>,
    ): String {
        logger.info("hello -> headers: $headers")
        return "Hello World"
    }
    @PostMapping(value = ["/person"])
    internal suspend fun person(
        @RequestBody person: Person,
        @RequestHeader headers: Map<String, String>,
    ): Person {
        logger.info("person -> headers: $headers, person: $person")
        return Person(name = "张书豪", age = 18, gender = "Male")
    }
}