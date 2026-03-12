import java.util.Properties
import java.time.LocalDateTime

plugins {
    id("java")
    application
    id("com.gradleup.shadow") version "8.3.0"
}

group = "com.example"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("com.example.lab10.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":string-utils"))
    
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.shadowJar {
    manifest {
        attributes(mapOf("Main-Class" to "com.example.lab10.Main"))
    }
}

// кастомная таска для генерации паспорта сборки
abstract class GenerateBuildInfoTask : DefaultTask() {
    @get:Input
    abstract val gitCommitHash: Property<String>

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @TaskAction
    fun generate() {
        val file = outputFile.get().asFile
        val props = Properties()

        // читаем старый билд чтобы сделать инкремент
        var buildNumber = 1
        if (file.exists()) {
            file.inputStream().use { props.load(it) }
            buildNumber = (props.getProperty("build.number")?.toIntOrNull() ?: 0) + 1
        }

        props.setProperty("build.number", buildNumber.toString())
        props.setProperty("build.user", System.getenv("USERNAME") ?: System.getenv("USER") ?: "какой-то чел")
        props.setProperty("build.os", System.getProperty("os.name"))
        props.setProperty("build.java", System.getProperty("java.version"))
        // юзаем импортированный LocalDateTime напрямую
        props.setProperty("build.date", LocalDateTime.now().toString())
        props.setProperty("build.message", "йоу, привет от грейдла!")
        props.setProperty("build.commit", gitCommitHash.get())

        // сохраняем в ресурсы
        file.parentFile.mkdirs()
        file.outputStream().use { props.store(it, "паспорт сборки") }

        println("======================================")
        println("паспорт сборки успешно сгенерирован!")
        println("проект: ${project.name}")
        println("версия gradle: ${project.gradle.gradleVersion}")
        println("сборка номер: $buildNumber")
        println("======================================")
    }
}

// достаем хэш гита (переписано на ProcessBuilder для новых версий java)
fun getGitHash(): String {
    return try {
        val process = ProcessBuilder("git", "rev-parse", "--short", "HEAD").start()
        process.inputStream.bufferedReader().use { it.readText().trim() }
    } catch (e: Exception) {
        "хэш-не-найден"
    }
}

// регистрируем таску
val generateBuildPassport by tasks.registering(GenerateBuildInfoTask::class) {
    gitCommitHash.set(getGitHash())
    outputFile.set(file("src/main/resources/build-passport.properties"))
}

// цепляем нашу таску к копированию ресурсов
tasks.named("processResources") {
    dependsOn(generateBuildPassport)
}

// чтобы сканер нормально читал из консоли при `gradle run`
tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}