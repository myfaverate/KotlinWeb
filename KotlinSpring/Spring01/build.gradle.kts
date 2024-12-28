plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.noarg") version "2.1.0"
}

group = "edu.tyut"
version = "unspecified"

repositories {
    mavenCentral()
}

noArg {
    annotation("edu.tyut.edu.tyut.annotation.NoArg")
}

dependencies {
    // druid
    implementation("com.alibaba:druid:1.2.23")
    // mysql driver
    implementation("com.mysql:mysql-connector-j:9.1.0")
    // Spring context
    implementation("org.springframework:spring-context:6.2.0")
    // SLF4J 依赖
    implementation("org.slf4j:slf4j-api:2.0.16")
    // logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.12")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}