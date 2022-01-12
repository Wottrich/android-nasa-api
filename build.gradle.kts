// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    `kotlin-dsl`
}

buildscript {

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
        defaultClasspath()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}