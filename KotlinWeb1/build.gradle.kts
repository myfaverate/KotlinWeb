plugins {
    kotlin("jvm") version "2.0.0"
    war
}

group = "edu.tyut"
version = "1.0-SNAPSHOT"

// repositories {
//     mavenCentral()
// }

dependencies {
    // https://mvnrepository.com/artifact/jakarta.servlet/jakarta.servlet-api
    providedCompile("jakarta.servlet:jakarta.servlet-api:6.1.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.war {
    webAppDirectory = file("src/main/webapp")
    // from("src/rootContent") // adds a file-set to the root of the archive
    // webInf { from("src/additionalWebInf") } // adds a file-set to the WEB-INF dir.
    // webXml = file("src/someWeb.xml") // copies a file to WEB-INF/web.xml
}

kotlin {
    jvmToolchain(21)
}