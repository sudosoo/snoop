dependencies {
    api(project(":pladder-domain"))
    implementation("org.springframework.boot:spring-boot-starter-security")

    // JSON Web Token (JWT) library - jjwt
    implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation ("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation ("io.jsonwebtoken:jjwt-jackson:0.11.5")

    //S3
    implementation ("io.awspring.cloud:spring-cloud-aws-s3:3.0.2")

    // fixture-monkey
    testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.17")
}
