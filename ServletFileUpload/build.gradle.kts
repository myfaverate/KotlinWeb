plugins {
    kotlin("jvm") version "2.1.0"
    alias(libs.plugins.orgGretty)
    war
}

group = "edu.tyut"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // commons-fileupload2-jakarta-servlet6 文件上传
    implementation("org.apache.commons:commons-fileupload2-jakarta-servlet6:2.0.0-M2")
    // SLF4J 依赖
    implementation("org.slf4j:slf4j-api:2.0.16")
    // logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.15")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}