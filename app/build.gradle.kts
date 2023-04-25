plugins {
    `application`

    kotlin("jvm") version "1.8.20"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))

    // https://github.com/ajalt/mordant
    implementation("com.github.ajalt.mordant:mordant:2.0.0-beta13")
}

application {
    mainClass.set("com.github.bkhablenko.outreach.proxx.ApplicationKt")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
}
