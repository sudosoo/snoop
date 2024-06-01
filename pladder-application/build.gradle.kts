dependencies {
    implementation(project(":pladder-domain"))
    implementation(project(":pladder-core"))

    compileOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // fixture-monkey
    testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter:1.0.14")

    // Springdoc OpenAPI
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    // Spring Boot Starter Validation
    implementation ("org.springframework.boot:spring-boot-starter-validation")

}
