plugins {
    kotlin("jvm")
    war
    application
}

group = "edu.tyut"
version = "1.0.0"

repositories {
    mavenCentral()
}

application {
    mainClass.set("edu.tyut.ApplicationKt")
}

tasks.war {
    println("archiveBaseName: $archiveBaseName")
    println("project.name: ${project.name}")
    archiveFileName.set("${project.name}.war")
}

dependencies {
    implementation(libs.tomcat)
    implementation(libs.logback)
    implementation(libs.slf4jApi)
    implementation(libs.julToSlf4j)
    implementation(libs.kotlinxCoroutines)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}