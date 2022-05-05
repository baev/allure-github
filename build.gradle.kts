buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
}

tasks.withType(Wrapper::class) {
    gradleVersion = "7.4.2"
}

group = "io.eroshenkoam"
version = version

plugins {
    java
    id("io.qameta.allure") version "2.9.6"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}


allure {
    version.set("2.17.3")
    adapter {
        aspectjVersion.set("1.9.9.1")

    }
}
//
//configure<AllureExtension> {
//    autoconfigure = true
//    aspectjweaver = true
//    version = "2.14.0"
//
//    useJUnit5 {
//        version = "2.14.0"
//    }
//
//    resultsDir = project.file("allure-results")
//
//}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.compileTestJava {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.jar {
    manifest {
        attributes(mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version
        ))
    }
}

tasks.test {
    ignoreFailures = true
    useJUnitPlatform {

    }
    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.config.strategy", "dynamic")
    systemProperty("junit.jupiter.extensions.autodetection.enabled", "true")
}


repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(platform("org.junit:junit-bom:5.8.2"))
    implementation("commons-io:commons-io:2.7")
    implementation("io.qameta.allure:allure-java-commons:2.14.0")
    implementation("org.junit.jupiter:junit-jupiter-api")
    implementation("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.junit.jupiter:junit-jupiter-params")
}
