plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.allopen") version "2.1.0"
    id("org.jetbrains.kotlin.plugin.noarg") version "2.1.0"
}

group = "edu.tyut"
version = "unspecified"

repositories {
    mavenCentral()
}

noArg {
    annotation("edu.tyut.annotation.DataClassOpen")
}
allOpen {
    annotation("edu.tyut.annotation.DataClassOpen")
}


dependencies {
    // SLF4J 依赖
    implementation("org.slf4j:slf4j-api:2.0.16")
    // logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.12")
    // mybatis
    implementation(libs.orgMybatis)
    // druid
    implementation("com.alibaba:druid:1.2.23")
    // mysql driver
    implementation("com.mysql:mysql-connector-j:9.1.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Exec>("runMyBatisGenerator") {
    println("${file(".").absoluteFile}")
    commandLine("D:/SoftWare/maven3/bin/mvn.cmd", "org.mybatis.generator:mybatis-generator-maven-plugin:1.4.1:generate", "-f", "src/main/resources/pom.xml")
    // doLast {
    //     exec {
    //         commandLine("D:/SoftWare/maven3/bin/mvn.cmd", "org.mybatis.generator:mybatis-generator-maven-plugin:1.4.1:generate", "-f", "src/main/resources/pom.xml")
    //     }
    // }
}


kotlin {
    jvmToolchain(21)
}