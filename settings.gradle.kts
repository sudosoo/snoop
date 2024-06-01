rootProject.name = "pladder"

include("boot")
include("pladder-presentation")
include("pladder-core")
include("pladder-application")
include("pladder-domain")

pluginManagement {
	val kotlinVersion :String by settings
	val springDependencyManagerVersion :String by settings
	val springBootVersion :String by settings

	resolutionStrategy{
		eachPlugin{
			when (requested.id.id){
				"io.spring.dependency-management" -> useVersion(springDependencyManagerVersion)
				"org.springframework.boot" -> useVersion(springBootVersion)
				"org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
				"org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
				"org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
				"org.gradle.core" -> useVersion(kotlinVersion)
				"org.jetbrains.kotlin.plugin.lombok" -> useVersion(kotlinVersion)
			}
		}
	}

	repositories {
		mavenCentral()
		gradlePluginPortal()
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}