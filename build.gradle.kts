// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsKotlinJvm) apply false
}
buildscript {
//    ext {
//        compose_ui_version = Versions.compose
//    }

    repositories {
        // Make sure that you have the following two repositories
        google() // Google's Maven repository
        mavenCentral() // Maven Central repository
        jcenter()
    }
    dependencies {
        // Add the dependency for the Google services Gradle plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}
