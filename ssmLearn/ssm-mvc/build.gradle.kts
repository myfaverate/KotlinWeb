plugins {
    kotlin("jvm")
    war
    application
}

group = "edu.tyut"
version = "1.0.1"

repositories {
    mavenCentral()
}

application {
    mainClass.set("edu.tyut.ApplicationKt")
}

// not problem
tasks.war {
    archiveFileName.set("${project.name}.war")
}
// have a problem
tasks.jar {
    archiveFileName.set("${project.name}.jar")
    manifest {
        attributes["Main-Class"] = "edu.tyut.ApplicationKt"
    }
    from(configurations.runtimeClasspath.get().map { zipTree(it) }) {
        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {
    implementation(libs.slf4jApi)
    implementation(libs.julToSlf4j)
    implementation(libs.logback)
    implementation(libs.springContext)
    implementation(libs.tomcat)
    implementation(libs.jasper)
    implementation(libs.springWebmvc)
    implementation(libs.kotlinxCoroutines)
    implementation(libs.kotlinxReactor)
    implementation(libs.kotlinReflect)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}