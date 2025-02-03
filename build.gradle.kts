import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    kotlin("jvm") version "2.1.0"
    alias(libs.plugins.querydsl)
    alias(libs.plugins.ksp)
    id("org.springframework.boot") version "3.4.+"
    kotlin("plugin.spring") version "2.1.0"
    kotlin("plugin.jpa") version "2.1.0"
}

group = "com.acme"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(libs.spring.boot.dependencies))

    implementation("com.acme:commonLib:1.0.0")

    // Spring
    implementation(libs.spring.starter.data.jpa)

    implementation(libs.querydsl.openfeign.core)
    ksp(libs.ksp.querydsl.codegen)

    testImplementation(kotlin("test"))
}

querydsl {
    querydslDefault = true
    jpa = true
    library = "io.github.openfeign.querydsl:querydsl-ksp-codegen:6.10.1"

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
        apiVersion.set(KotlinVersion.KOTLIN_2_1)
        languageVersion.set(KotlinVersion.KOTLIN_2_1)
    }
}