plugins {
    // Apply the shared build logic from a convention plugin.
    // The shared code is located in `buildSrc/src/main/kotlin/kotlin-jvm.gradle.kts`.
    alias(notation = libs.plugins.conventionKotlinJvm)
    // Apply Kotlin Serialization plugin from `gradle/libs.versions.toml`.
    alias(libs.plugins.kotlinPluginSerialization)
}

dependencies {
    // Apply the kotlinx bundle of dependencies from the version catalog (`gradle/libs.versions.toml`).
    implementation(dependencyNotation = libs.bundles.kotlinxEcosystem)
    testImplementation(kotlin("test"))
}