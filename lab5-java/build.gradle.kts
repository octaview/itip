plugins {
    id("java")
    application
}
group = "com.example"
version = "1.0.0"
java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }
application { mainClass.set("com.example.lab5.App") }
repositories { mavenCentral() }