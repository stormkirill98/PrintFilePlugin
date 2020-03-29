plugins {
    java
    id("org.jetbrains.intellij") version "0.4.17"
    kotlin("jvm") version "1.3.70"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

buildscript {
    repositories {
        flatDir {
            dirs("./plugins")
        }
    }

    dependencies {
        classpath("org.example:print-file:1.0")
    }
}

apply {
    plugin("print-file")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2019.3.3"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
          Add change notes here.<br>
          <em>most HTML tags may be used</em>
    """)
}