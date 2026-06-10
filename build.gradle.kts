import java.util.Properties
import kotlin.apply

plugins {
    java
    application
    `java-library`
    id("org.javamodularity.moduleplugin") version "1.8.12"
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.beryx.jlink") version "2.25.0"
}

group = "io.github.gleidsonmt"

val project = Properties().apply {
    file("project.properties").inputStream().use { load(it) }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
}

javafx {
    version = "23.0.2"
    modules("javafx.controls", "javafx.fxml", "javafx.web", "javafx.swing")
}

dependencies {
    implementation(fileTree("vendor/colors-1.0.jar"));
    compileOnly("org.jetbrains:annotations:26.1.0")
    implementation(project("glad"))
    implementation(project("presentation"))


    // https://mvnrepository.com/artifact/org.yaml/snakeyaml
    implementation("org.yaml:snakeyaml:2.5")
}

application {
    mainModule = "io.github.gleidsonmt.dashboardfx"
    mainClass = "io.github.gleidsonmt.dashboardfx.App"
}

val requestedTasks = gradle.startParameter.taskNames

val runMode = when {
    requestedTasks.any { it == "debug" || it.endsWith(":debug") } -> "debug"
    requestedTasks.any { it == "log" || it.endsWith(":log") } -> "log"
    else -> "level-off"
}

// if another project is run, disable the run tasks
subprojects {
    tasks.configureEach {
        enabled = !name.contains("run")
    }
}
//
jlink {
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))

    launcher {
        name = "DashboardFx"
    }

    jpackage {
        installerType = "exe"
        appVersion = version as String

        installerOptions = listOf(
            "--description", "A dashboard made with JavaFx UI Toolkit.",
            "--copyright", "Copyright © 2025 GLEIDSON NEVES DA SILVEIRA"
        )
    }

    addExtraDependencies("javafx")
}