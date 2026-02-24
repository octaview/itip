plugins {
    id("java")
    application
}
group = "com.example"
version = "1.0.0"
java { toolchain { languageVersion.set(JavaLanguageVersion.of(21)) } }
application { mainClass.set("com.example.lab6.App") }
repositories { mavenCentral() }
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}