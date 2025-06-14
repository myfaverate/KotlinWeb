plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSluginSpring)
    alias(libs.plugins.orgSpringframeworkBoot)
    alias(libs.plugins.ioSpringDependencyManagement)
    alias(libs.plugins.orgGraalvmBuildtoolsNative)
}

group = "edu.tyut"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springBootStarterWebFlux) {
        exclude(group = libs.springBootStarterReactorNetty.get().group, module = libs.springBootStarterReactorNetty.get().name)
    }
    implementation(libs.springBootStarterJetty)
    implementation(libs.jacksonModuleKotlin)
    implementation(libs.reactorKotlinExtensions)
    implementation(libs.kotlinReflect)
    implementation(libs.kotlinxCoroutinesReactor)


    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.reactorTest)
    testImplementation(libs.kotlinTestJunit5)
    testImplementation(libs.kotlinxCoroutinesTest)
    testRuntimeOnly(libs.junitPlatformLauncher)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}