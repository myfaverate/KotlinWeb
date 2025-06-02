plugins {
    kotlin("jvm")
}

group = "edu.tyut"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springContext)
    implementation(libs.slf4jApi)
    implementation(libs.logback)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}