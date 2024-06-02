dependencies {
    implementation(project(":pladder-domain"))
    implementation(project(":pladder-core"))

    compileOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // fixture-monkey
    testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter:1.0.14")
}
