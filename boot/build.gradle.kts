tasks.bootJar {
    enabled = true
}
tasks.jar {
    enabled = false
}

dependencies {
    implementation(project(":pladder-presentation"))
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
}