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

graalvmNative {
    binaries {
        named("main") {
            // TODO https://github.com/netty/netty/issues/15331
            buildArgs.add("--initialize-at-run-time=sun.net.dns.ResolverConfigurationImpl")
            buildArgs.add("-O2")
        }
    }
}

dependencies {
    implementation(libs.spring.boot.starter.webflux) {
        exclude(group = libs.springBootStarterReactorNetty.get().group, module = libs.springBootStarterReactorNetty.get().name)
    }
    implementation(libs.springBootStarterJetty)
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
    implementation(libs.spring.boot.starter.thymeleaf)

    implementation(libs.kotlinxSerializationJson)
    implementation(libs.springBootStarterDataRedisReactive)

    // TODO remove issue
    implementation("org.postgresql:r2dbc-postgresql:1.0.7.RELEASE")

    // kotlin html
    // Not very user-friendly
    implementation(libs.kotlinxHtmlJvm)

    testImplementation (libs.kotlinxCoroutinesTest)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.kotlin.test.junit5)
    testRuntimeOnly(libs.junit.platform.launcher)
}


tasks.named<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
    environment.putAll(mapOf(
        "JAVA_TOOL_OPTIONS" to "-XX:+UseZGC",
        "BP_SPRING_AOT_ENABLED" to "true",
        "BP_JVM_CDS_ENABLED" to "true",
    ))
    logger.warn("env: ${environment.get()}")
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
