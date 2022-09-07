buildscript {
    if (gradle.includedBuilds.any { it.name == "plugins" }) {
        plugins {
            id("com.hivemq.version-updater")
        }
    }
}

plugins {
    `java-library`
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin")
    id("io.github.sgtsilvio.gradle.defaults")
    id("com.github.sgtsilvio.gradle.metadata")
    id("com.github.sgtsilvio.gradle.javadoc-links")
    id("com.github.hierynomus.license")
}

plugins.withId("com.hivemq.version-updater") {
    project.ext.set("versionUpdaterFiles", arrayOf("README.adoc"))
}

group = "com.hivemq"
description = "SDK for the development of HiveMQ Google Cloud Pub/Sub Extension customizations"

metadata {
    readableName.set("HiveMQ Google Cloud Pub/Sub Extension Customization SDK")
    organization {
        name.set("HiveMQ GmbH")
        url.set("https://www.hivemq.com/")
    }
    license {
        apache2()
    }
    developers {
        register("hurtadosanti") {
            fullName.set("Santiago Hurtado")
            email.set("santiago.hurtado@hivemq.com")
        }
        register("Donnerbart") {
            fullName.set("David Sondermann")
            email.set("david.sondermann@hivemq.com")
        }
        register("YannickWeber") {
            fullName.set("Yannick Weber")
            email.set("yannick.weber@hivemq.com")
        }
        register("Florian-Limpoeck") {
            fullName.set("Florian Limpöck")
            email.set("florian.limpoeck@hivemq.com")
        }
        register("mario-schwede-hivemq") {
            fullName.set("Mario Schwede")
            email.set("mario.schwede@hivemq.com")
        }
    }
    github {
        org.set("hivemq")
        repo.set("hivemq-gcp-pubsub-extension-customization-sdk")
        issues()
    }
}

/* ******************** dependencies ******************** */

val internalPlatform by configurations.creating {
    isVisible = false
    isCanBeConsumed = false
    isCanBeResolved = false
}

configurations {
    compileClasspath.get().extendsFrom(internalPlatform)
    runtimeClasspath.get().extendsFrom(internalPlatform)
    testCompileClasspath.get().extendsFrom(internalPlatform)
    testRuntimeClasspath.get().extendsFrom(internalPlatform)
    javadocLinks.get().shouldResolveConsistentlyWith(runtimeClasspath.get())
}

repositories {
    mavenCentral()
}

dependencies {
    internalPlatform(platform("com.hivemq:hivemq-main-platform"))

    api("com.hivemq:hivemq-extension-sdk:${version}")
    api("org.slf4j:slf4j-api")
}

/* ******************** java ******************** */

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Jar>().configureEach {
    manifest.attributes(
        "Implementation-Title" to project.name,
        "Implementation-Vendor" to metadata.organization.get().name.get(),
        "Implementation-Version" to project.version
    )
}

tasks.javadoc {
    title = "${metadata.readableName.get()} ${project.version} API"

    doLast { // javadoc search fix for jdk 11 https://bugs.openjdk.java.net/browse/JDK-8215291
        copy {
            from(destinationDir!!.resolve("search.js"))
            into(temporaryDir)
            filter { line -> line.replace("if (ui.item.p == item.l) {", "if (item.m && ui.item.p == item.l) {") }
        }
        delete(destinationDir!!.resolve("search.js"))
        copy {
            from(temporaryDir.resolve("search.js"))
            into(destinationDir!!)
        }
    }
}

/* ******************** publishing ******************** */

publishing {
    publications {
        register<MavenPublication>("maven") {
            from(components["java"])

            versionMapping {
                usage("java-api") {
                    fromResolutionResult()
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
}

signing {
    val signKey: String? by project
    val signKeyPass: String? by project
    useInMemoryPgpKeys(signKey, signKeyPass)
    sign(publishing.publications["maven"])
}

nexusPublishing {
    repositories {
        sonatype()
    }
}

/* ******************** checks ******************** */

license {
    header = file("HEADER")
    mapping("java", "SLASHSTAR_STYLE")
}
