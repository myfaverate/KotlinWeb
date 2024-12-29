plugins {
    kotlin("jvm")
    // alias(libs.plugins.allopen) // 支持 mybatis 懒加载
    alias(libs.plugins.orgGretty)
    war
}

group = "edu.tyut"
version = "unspecified"

repositories {
    mavenCentral()
}

// allOpen {
//     annotation("edu.tyut.edu.tyut.annotation.NoArg")
// }

dependencies {
    // spring-webmvc
    implementation(libs.springMVC)
    // spring-jdbc
    implementation(libs.springJDBC)
    // spring-aspects
    implementation(libs.springAspects)
    // // mybatis
    implementation(libs.mybatis)
    // mybatisSpring
    implementation(libs.mybatisSpring)
    // pageHelper
    implementation(libs.pageHelper)
    // druid
    implementation(libs.druid)
    // mysql
    @Suppress("VulnerableLibrariesLocal", "RedundantSuppression")
    implementation(libs.mysql)
    // jacksonModuleKotlin
    implementation(libs.jacksonModuleKotlin)
    // thymeleafSpring
    implementation(libs.thymeleafSpring)
    // SLF4J 依赖
    implementation(libs.slf4jApi)
    // logback-classic
    implementation(libs.logbackClassic)
    testImplementation(libs.springTest)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}