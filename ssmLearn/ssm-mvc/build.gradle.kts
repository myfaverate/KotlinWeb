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

tasks.jar {
    manifest {
        attributes["Main-Class"] = "edu.tyut.ApplicationKt"
    }
    // 排除签名文件
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }) {
        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {
    implementation(libs.slf4jApi)
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