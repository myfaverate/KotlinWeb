plugins {
    kotlin("jvm") version "2.0.21"
}

group = "edu.tyut"
version = "1.0-SNAPSHOT"

// repositories {
//     mavenCentral()
// }

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:2.0.21-1.0.28")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}