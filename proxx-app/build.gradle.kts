plugins {
    `application`

    kotlin("jvm") version "1.8.20"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":proxx-core"))
}

application {
    mainClass.set("com.github.bkhablenko.outreach.proxx.Application")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }
}
