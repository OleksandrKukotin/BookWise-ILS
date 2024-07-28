plugins {
    id("org.springframework.boot") version "3.3.2"
}

dependencies {
    implementation(project(":rest-api"))
    implementation(project(":web-api"))
    implementation(project(":domain"))
    implementation(project(":persistence"))
}

tasks.bootJar {
    enabled = true
}
