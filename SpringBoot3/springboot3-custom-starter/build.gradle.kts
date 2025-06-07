plugins {
    kotlin("jvm")
    `maven-publish`
}

group = "edu.tyut"
version = "1.0.0"

repositories {
    mavenCentral()
}

publishing{
    repositories{
        maven {
            url = uri(path = "file://${rootDir}/repository")
        }
    }
    publications{
        register<MavenPublication>("kotlin"){
            from(components["kotlin"])
            groupId = project.group.toString().lowercase()
            artifactId = project.name
            version =  "0.0.1"
            artifact(tasks.kotlinSourcesJar)
        }
    }
}

dependencies {
    implementation(libs.slf4jApi)
    implementation(libs.logback)
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.5.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}