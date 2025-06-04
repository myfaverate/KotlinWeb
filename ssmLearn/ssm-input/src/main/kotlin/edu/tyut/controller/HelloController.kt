package edu.tyut.controller

import edu.tyut.bean.Person
import edu.tyut.bean.User
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
private class HelloController {
    private val logger: Logger = LoggerFactory.getLogger(HelloController::class.java)
    @GetMapping(value = ["/hello"])
    @ResponseBody
    private suspend fun hello(): String = withContext(Dispatchers.Default) {
        return@withContext "Hello, World 世界！"
    }
    @GetMapping(value = ["/data"])
    @ResponseBody
    private suspend fun data(
        @RequestParam(value = "name")
        name: String,
        @RequestParam(value = "age")
        age: Int,
    ): String {
        delay(timeMillis = 5000L)
        logger.info("Data called name: {}, age: {}", name, age)
        return "Data called name: $name, age: $age"
    }
    @GetMapping(value = ["/dataList"])
    @ResponseBody
    private suspend fun dataList(
        @RequestParam(value = "names")
        names: List<String>,
    ): String {
        coroutineScope {  }
        logger.info("Data called names: {}", names)
        return "Data called names: $names"
    }

    @GetMapping(value = ["/getUser"])
    @ResponseBody
    private suspend fun getUser(user: User): String {
        coroutineScope {  }
        logger.info("Data called user: {}", user)
        return user.toString()
    }

    @PostMapping(value = ["/getPerson"])
    @ResponseBody
    private suspend fun getPerson(
        @RequestBody person: Person
    ): String {
        coroutineScope {  }
        logger.info("Data called person: {}", person)
        return person.toString()
    }

    @GetMapping(value = ["/getCookie"])
    @ResponseBody
    private suspend fun getCookie(
        @CookieValue(value = "cookieName") value: String
    ): String {
        coroutineScope {  }
        logger.info("Data called cookieName: {}", value)
        return value
    }

    @GetMapping(value = ["/setCookie"])
    @ResponseBody
    private suspend fun setCookie(
        response: HttpServletResponse,
    ): String {
        val cookie = Cookie("cookieName", "cookieValue")
        response.addCookie(cookie)
        coroutineScope {  }
        logger.info("Data called cookie: {}", cookie)
        return cookie.toString()
    }

    @GetMapping(value = ["/index"])
    private suspend fun index(
        request: HttpServletRequest
    ): String = withContext(Dispatchers.Default) {
        logger.info("Data called index: {}", Thread.currentThread().name)
        request.setAttribute("data", "zsh")
        return@withContext "index"
    }
    @GetMapping(value = ["/home"])
    private suspend fun home(): String = withContext(Dispatchers.Default) {
        logger.info("Data called home: {}", Thread.currentThread().name)
        return@withContext "redirect:index"
        // return@withContext "forward:index"
    }

    @GetMapping(value = ["/persons"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    private suspend fun persons(): List<Person> = withContext(Dispatchers.Default) {
        logger.info("Data called user user: {}", Thread.currentThread().name)
        return@withContext listOf(Person(name = "zss", age = 17, gender = "男"), Person(name = "zss", age = 17, gender = "男"))
    }
}