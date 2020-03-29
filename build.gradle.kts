plugins {
    id("org.jetbrains.kotlin.jvm").version("1.3.21")
}

repositories {
    jcenter()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.javalin:javalin:3.8.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.9")
    implementation("org.slf4j:slf4j-simple:1.7.28")
    implementation("io.swagger.core.v3:swagger-core:2.0.9")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}
