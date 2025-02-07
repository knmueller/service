import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm") version "2.1.0"
    alias(libs.plugins.ksp)
    id("org.springframework.boot") version "3.4.+"
    kotlin("plugin.spring") version "2.1.0"
    kotlin("plugin.jpa") version "2.1.0"
}

group = "com.acme"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(platform(libs.spring.boot.dependencies))

    implementation("com.acme:commonLib:1.0.0")

    // Spring
    implementation(libs.spring.starter.data.jpa)

    implementation(libs.querydsl.openfeign.core)
    implementation(libs.querydsl.openfeign.jpa)
    ksp(libs.ksp.querydsl.codegen)
    ksp(variantOf(libs.querydsl.openfeign.apt) { classifier("jakarta") })

    testImplementation(kotlin("test"))
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
        apiVersion.set(KotlinVersion.KOTLIN_2_1)
        languageVersion.set(KotlinVersion.KOTLIN_2_1)
    }
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.test {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
}

tasks.test {
    useJUnitPlatform()
}