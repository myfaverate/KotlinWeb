plugins {
    kotlin("jvm") version "2.0.0"
}

group = "edu.tyut"
version = "1.0-SNAPSHOT"

// repositories {
//     mavenCentral()
// }

dependencies {
    // dom4j
    implementation("org.dom4j:dom4j:2.1.4")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}