plugins {
    id("java")
    application
}

group = "com.example"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application { mainClass.set("com.example.lab4.App") }
repositories { mavenCentral() }