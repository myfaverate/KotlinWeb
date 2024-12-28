plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    id(id = "com.google.devtools.ksp") version "2.0.21-1.0.28"
    war
}

group = "edu.tyut"
version = "1.0-SNAPSHOT"

// repositories {
//     mavenCentral()
// }

dependencies {

    // servlet
    providedCompile("jakarta.servlet:jakarta.servlet-api:6.1.0")
    // druid
    implementation("com.alibaba:druid:1.2.23")
    // mysql driver
    implementation("com.mysql:mysql-connector-j:8.4.0")

    // SLF4J 依赖
    implementation("org.slf4j:slf4j-api:2.0.16")
    // logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.12")
    // Kotlin 协程核心库
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")

    // 使用自定义注解处理器
    implementation(dependencyNotation = project(path = ":Processor"))
    ksp(dependencyNotation = project(path = ":Processor"))

    // kotlin 原生 json 解析器
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    testImplementation(kotlin("test"))

}


ksp {
    arg(k = "version", v = "1.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.war {
    webAppDirectory = file("src/main/webapp")
}

kotlin {
    jvmToolchain(21)
}