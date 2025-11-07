plugins {
    kotlin("jvm")
    id("java-library")
}

repositories {
    mavenCentral()
}

kotlin {
    // Keep JVM target 17 for consistency
    jvmToolchain(17)
}

dependencies {
    testImplementation(kotlin("test-junit5"))

    // Explicitly add the JUnit Platform Launcher
    testImplementation("org.junit.platform:junit-platform-launcher:1.10.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}