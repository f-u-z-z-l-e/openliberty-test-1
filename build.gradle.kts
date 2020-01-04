buildscript {
    repositories {
        mavenCentral()
        maven {
            name = "Sonatype Nexus Snapshots"
            setUrl("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
    dependencies {
        classpath("io.openliberty.tools:liberty-gradle-plugin:3.0-M1")
    }
}

apply(plugin = "net.wasdev.wlp.gradle.plugins.Liberty")

plugins {
    java
    war
    id("ch.fuzzle.gradle.semver") version "0.3.2"
    id("ch.fuzzle.gradle.docker-plugin") version "0.1.3"
}

group = "ch.fuzzle.openliberty"

repositories {
    mavenCentral()
}

dependencies {
    libertyRuntime("io.openliberty:openliberty-runtime:19.0.0.12")
    providedCompile("io.openliberty.features:jpa-2.2:19.0.0.12")
    providedCompile("io.openliberty.features:microProfile-3.2:19.0.0.12")

    testImplementation("commons-httpclient:commons-httpclient:3.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.2")

}

tasks.compileJava.configure {
    sourceCompatibility = "13"
    targetCompatibility = "13"
    options.encoding = "UTF-8"
    options.compilerArgs.add("-Xlint:unchecked")
    options.compilerArgs.add("-Xlint:deprecation")
}

// libertyRuntime is unknown to kotlin dsl
fun DependencyHandler.libertyRuntime(dependencyNotation: Any): Dependency? = add("libertyRuntime", dependencyNotation)

tasks.clean.configure {
    dependsOn("libertyStop")
}

tasks {
    // Use the built-in JUnit support of Gradle.
    "test"(Test::class) {
        useJUnitPlatform()
    }
}

// https://localhost:8543/ibm/api/validation/dataSource/DefaultDataSourceA
// http://172.22.0.20:8182/test1/servlet