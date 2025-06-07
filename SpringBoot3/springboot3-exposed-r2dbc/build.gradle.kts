plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSluginSpring)
    alias(libs.plugins.orgSpringframeworkBoot)
    alias(libs.plugins.ioSpringDependencyManagement)
    alias(libs.plugins.orgGraalvmBuildtoolsNative)
}

group = "edu.tyut"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springBootStarterWebFlux)
    implementation(libs.springBootStarterDataR2dbc)
    implementation(libs.jacksonModuleKotlin)
    implementation(libs.kotlinReflect)
    implementation(libs.exposedSpringbootStarter)
    implementation(libs.exposedR2dbc)
    implementation(libs.r2dbcMysql)
    // implementation(libs.mysqlJ)

    implementation(libs.kotlinxCoroutinesReactor)

    // TODO remove issue
    implementation("org.postgresql:r2dbc-postgresql:1.0.7.RELEASE")

    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.kotlinTestJunit5)
    testRuntimeOnly(libs.junitPlatformLauncher)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}