import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    id("org.openapi.generator") version "7.6.0"
}

group = "ru.sindenis"
version = "0.0.1-SNAPSHOT"

val openapiVersion = "1.7.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.liquibase:liquibase-core")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.telegram:telegrambots-spring-boot-starter:6.8.0")
    implementation("org.telegram:telegrambotsextensions:6.8.0")
    implementation("io.github.microutils:kotlin-logging:1.4.3")
    implementation("org.springdoc:springdoc-openapi-data-rest:$openapiVersion")
    runtimeOnly("org.postgresql:postgresql")
    implementation("com.ToxicBakery.library.bcrypt:bcrypt-jvm:1.0.9")
    implementation("io.jsonwebtoken:jjwt:0.12.3")
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generated/source/openapi/src/main/kotlin/")
        }
    }
}

tasks {

    test {
        useJUnitPlatform()
    }

    compileKotlin {
        dependsOn("openApiCodeGenerate")

        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "21"
        }
    }

    register("openApiCodeGenerate", GenerateTask::class) {
        generatorName.set("kotlin-spring")
        library.set("spring-boot")
        groupId.set("ru.sindenis")
        generateApiTests.set(false)
        inputSpec.set("$rootDir/src/main/resources/openapi/openapi.yml")
        outputDir.set("$buildDir/generated/source/openapi")
        apiPackage.set("ru.sindenis.mailoyalty.restapi")
        modelPackage.set("ru.sindenis.mailoyalty.restapi.dto")
        configOptions.set(
            mapOf(
                "skipDefaultInterface" to "true",
                "interfaceOnly" to "true",
                "useSpringBoot3" to "true",
                "requestMappingMode" to "api_interface",
                "serializationLibrary" to "jackson",
                "openApiNullable" to "false",
                "useTags" to "true",
                "useBeanValidation" to "true",
                "documentationProvider" to "springdoc",
                "dateLibrary" to "java8",
            )
        )
    }
}
