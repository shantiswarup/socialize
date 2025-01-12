val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.1.0"
    id("io.ktor.plugin") version "3.0.2"
}

group = "com.socialize"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.jetty.jakarta.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

jib {
    from {
        platforms {
            platform {
                architecture = "arm64"
                os = "linux"
            }
        }
    }
    container {
        ports = listOf("4000")
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-jetty-jakarta-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml-jvm")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
