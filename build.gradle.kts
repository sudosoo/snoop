import org.springframework.boot.gradle.tasks.bundling.BootJar
val kotestVersion = "5.7.2"

plugins {
	id("org.springframework.boot") apply false
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
}

allprojects {
	group = "com.api"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
		mavenLocal()
		maven ("https://repo.spring.io/release")
		maven ("https://repo.spring.io/milestone/")
		maven ("https:/jitpack.io")
	}
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

	dependencyManagement {
		imports {
			mavenBom("org.springframework.boot:spring-boot-dependencies:3.0.4")
			mavenBom("com.google.guava:guava-bom:31.1-jre")
			mavenBom("org.jetbrains.kotlin:kotlin-bom:1.7.22")
		}
	}

	configurations.all {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}

	tasks.register("prepareKotlinBuildScriptModel"){}

	tasks{
		val bootJar by getting(BootJar::class){
			enabled = false
		}

		val jar by getting(Jar::class){
			enabled = true
		}
	}

	java {
		sourceCompatibility = JavaVersion.VERSION_17
	}

	//자바 모듈
	if (name == "takeItEasy-domain") {


		//코틀린 모듈
	}else{
		apply(plugin = "org.jetbrains.kotlin.jvm")
		apply(plugin = "org.jetbrains.kotlin.plugin.spring")

		dependencies {
			implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
			implementation("org.jetbrains.kotlin:kotlin-reflect")
			implementation("org.jetbrains.kotlin:kotlin-stdlib")
		}

		configurations {
			compileOnly {
				extendsFrom(configurations.annotationProcessor.get())
			}
		}

		tasks{

			compileKotlin {
				kotlinOptions.jvmTarget = "17"
				kotlinOptions.freeCompilerArgs = listOf(
					"-Xjsr305=strict",
				)
			}
		}
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-validation")
		implementation("org.springframework.boot:spring-boot-starter-actuator")
		implementation("org.springframework.boot:spring-boot-devtools")
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.springframework.boot:spring-boot-starter-web")

		testImplementation("org.junit.jupiter:junit-jupiter-api")
		testImplementation("org.springframework.boot:spring-boot-starter-test")

		// Springdoc OpenAPI
		implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

		// Spring Boot Starter Validation
		implementation ("org.springframework.boot:spring-boot-starter-validation")

		// Test framework
		testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
		testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
		testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")

		// Assertion library
		testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")

		// Mockk
		testImplementation("io.mockk:mockk:1.13.5")



	}

	tasks.test {
		useJUnitPlatform()
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

}
