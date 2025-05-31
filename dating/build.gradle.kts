plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.serialization)
	alias(libs.plugins.kotlin.plugin.spring)
	alias(libs.plugins.org.springframework.boot)
	alias(libs.plugins.io.spring.dependency.management)
	alias(libs.plugins.org.graalvm.buildtools.native)
}

group = "edu.tyut"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(JavaVersion.VERSION_21.toString().toInt())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.spring.webmvc)
	implementation(libs.spring.boot.starter)
	implementation(libs.spring.boot.starter.jetty)
	implementation(libs.spring.boot.starter.jdbc)
	implementation(libs.kotlinx.serialization.json)
	implementation(libs.kotlin.reflect)
	implementation(dependencyNotation = libs.exposed.spring.boot.starter) {
		exclude(group = libs.hikariCP.get().group, module = libs.hikariCP.get().name)
	}
	implementation(libs.exposed.core)
	implementation(libs.exposed.jdbc)
	implementation(libs.mysql.connector.j)
	implementation(libs.kotlinx.coroutines.reactor)
	testImplementation(libs.spring.boot.starter.test)
	testImplementation(libs.kotlin.test.junit5)
	testRuntimeOnly(libs.junit.platform.launcher)
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
