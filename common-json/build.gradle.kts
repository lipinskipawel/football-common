plugins {
    id("buildlogic.java-library-conventions")
}

dependencies {
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.18.1")

    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.1")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.4.1")
}
