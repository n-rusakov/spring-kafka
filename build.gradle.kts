plugins {
	java
	id("org.springframework.boot") version "3.2.4" apply false
	id("io.spring.dependency-management") version "1.1.4" apply false
}

subprojects {

	apply {
		plugin("java")
		plugin("org.springframework.boot")
		plugin("io.spring.dependency-management")
	}

	group = "com.example.springkafka"
	version = "1.0"

	java {
		sourceCompatibility = JavaVersion.VERSION_17
	}

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	repositories {
		mavenCentral()
	}

	dependencies {

		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")

		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.springframework.kafka:spring-kafka-test")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

}





