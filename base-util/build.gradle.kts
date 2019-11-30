import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile("org.apache.httpcomponents:httpclient:4.5.10")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}