plugins {
    id("org.springframework.boot") version "3.2.4"
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
