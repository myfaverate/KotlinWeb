plugins {
    kotlin("jvm")
    alias(libs.plugins.orgGretty)
    war
}

group = "edu.tyut"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    // spring-webmvc
    implementation("org.springframework:spring-webmvc:6.2.1")
    // SLF4J 依赖
    implementation("org.slf4j:slf4j-api:2.0.16")
    // logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.15")
    // jackson-module-kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}