// Top-level build file where you can add configuration options common to all sub-projects/modules.
ext {
    set("baseUrl", "https://api.exchangerate.host/")
}
plugins {
    id("com.android.application") version "7.2.1" apply false
    id("com.android.library") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    kotlin("kapt") version "1.8.0"

    // plugin for updating .toml file.
    id("com.github.ben-manes.versions") version "0.41.0"
    id("nl.littlerobots.version-catalog-update") version "0.7.0"
}
