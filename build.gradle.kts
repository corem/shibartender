val ktor_version: String by project
val koin_version: String by project
val arrow_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kmongo_version: String by project
val jbcrypt_version: String by project
//val hoplite_version: String by project
val kotest_version: String by project

plugins {
    application
    kotlin("jvm") version "1.8.0"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
}

group = "com.shibartender"
version = "0.0.1"
application {
    mainClass.set("com.shibartender.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")

//    implementation("com.sksamuel.hoplite:hoplite-core:$hoplite_version")
//    implementation("com.sksamuel.hoplite:hoplite-hocon:$hoplite_version")

    testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")

    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")

    implementation("io.arrow-kt:arrow-core:$arrow_version")

    implementation("org.mindrot:jbcrypt:$jbcrypt_version")

    implementation("org.litote.kmongo:kmongo:$kmongo_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}