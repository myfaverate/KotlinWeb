plugins {
    kotlin("jvm") version "2.0.0"
}

group = "edu.tyut"
version = "1.0-SNAPSHOT"

// repositories {
//     mavenCentral()
// }

dependencies {
    // druid
    implementation("com.alibaba:druid:1.2.23")
    // mysql driver
    implementation("com.mysql:mysql-connector-j:8.3.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}