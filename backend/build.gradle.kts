plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    application
}

group = "kz.kbtu.wsp.backend"
version = "0.0.1"

application {
    mainClass.set("kz.kbtu.wsp.backend.ApplicationKt")
}

repositories {
    mavenCentral()
}

val ktorVersion = "3.0.3"

dependencies {
    // ── Server ───────────────────────────────────────────────────────────────
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-cors-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktorVersion")

    // ── HTTP client (for calling KBTU WSP) ───────────────────────────────────
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-cio-jvm:$ktorVersion")

    // ── HTML parsing ─────────────────────────────────────────────────────────
    implementation("org.jsoup:jsoup:1.18.3")

    // ── Logging ──────────────────────────────────────────────────────────────
    implementation("org.slf4j:slf4j-simple:2.0.16")

    // ── Tests ────────────────────────────────────────────────────────────────
    testImplementation("io.ktor:ktor-server-test-host-jvm:$ktorVersion")
    testImplementation(kotlin("test-junit"))
}

kotlin {
    jvmToolchain(17)
}