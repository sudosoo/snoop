dependencies {
    //Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")

    // JSON Web Token (JWT) library - jjwt
    implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly ("io.jsonwebtoken:jjwt-jackson:0.11.5")

    //S3
    implementation ("io.awspring.cloud:spring-cloud-aws-s3:3.0.2")

    // jasypt
    implementation ("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
}