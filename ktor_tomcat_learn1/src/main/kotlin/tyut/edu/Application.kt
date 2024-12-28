package tyut.edu

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.tomcat.jakarta.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureSerialization()
    configureTemplating()
    configureDatabases()
    configureRouting()
}
