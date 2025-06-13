pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        maven { url = uri(path = "https://maven.aliyun.com/repository/public/") }
        maven { url = uri(path = "https://maven.aliyun.com/repositories/google") }
        maven { url = uri(path = "https://maven.aliyun.com/repositories/central") }
        google()
        mavenCentral()
    }
}
rootProject.name = "spring_boot_modern"
