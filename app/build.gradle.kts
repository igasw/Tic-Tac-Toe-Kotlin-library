plugins {
    kotlin("jvm")

    // adds the 'run' task
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":lib"))
}

application {
    mainClass.set("org.jetbrains.kotlinx.tictactoe.MainKt")
}

kotlin {
    jvmToolchain(17)
}

// tells Gradle's 'run' task to read input from the console and forward it to your Kotlin application
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}