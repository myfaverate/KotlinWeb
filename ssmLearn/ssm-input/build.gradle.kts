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
    implementation(libs.tomcat)
    implementation(libs.slf4jApi)
    implementation(libs.julToSlf4j)
    implementation(libs.logback)
    implementation(libs.springWebmvc)
    implementation(libs.kotlinxCoroutines)
    implementation(libs.kotlinxReactor)
    implementation(libs.kotlinReflect)
    implementation(libs.kotlinxSerialization)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}