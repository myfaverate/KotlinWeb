plugins {
    kotlin("jvm")
    application
    alias(libs.plugins.kotlinPluginSerialization)
}

group = "edu.tyut"
version = "1.0.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("edu.tyut.ApplicationKt")
}

dependencies {
    implementation(libs.springWebmvc)
    implementation(libs.springJdbc)
    implementation(libs.springAspects)

    implementation(libs.slf4jApi)
    implementation(libs.julToSlf4j)
    implementation(libs.logback)
    implementation(libs.tomcat)

    implementation(libs.kotlinxCoroutines)
    implementation(libs.kotlinxReactor)
    implementation(libs.kotlinReflect)
    implementation(libs.kotlinxSerialization)

    implementation(libs.thymeleafSpring)
    implementation(libs.hikariCP)
    implementation(libs.mysql)


    implementation(libs.exposedCore)
    // implementation(libs.exposedR2dbc)
    implementation(libs.exposedJdbc)



    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}