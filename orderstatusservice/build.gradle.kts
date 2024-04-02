dependencies {
    implementation("org.springframework.boot:spring-boot-starter")

    implementation("org.springframework.kafka:spring-kafka")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.0")

    implementation(project(":orderevent"))
}

