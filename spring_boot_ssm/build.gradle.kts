plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.dependency.management)
    alias(libs.plugins.graalvm.native)
}

group = "edu.tyut"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.kotlin.reflect)
    implementation(libs.javaJwt)
    implementation(libs.kotlinxCoroutinesReactor)

    implementation(libs.exposedSpringbootStarter)
    implementation(libs.exposedR2dbc)
    implementation(libs.exposedKotlinDatetime)
    implementation(libs.kotlinxDatetime)
    implementation(libs.r2dbcMysql)
    implementation(libs.springBootStarterDataR2dbc)

    // TODO remove issue
    implementation("org.postgresql:r2dbc-postgresql:1.0.7.RELEASE")

    testImplementation (libs.kotlinxCoroutinesTest)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.kotlin.test.junit5)
    testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
    jvmToolchain(jdkVersion = 21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
