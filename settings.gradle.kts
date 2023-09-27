pluginManagement {
    plugins {
        id("io.github.gradle-nexus.publish-plugin") version "${extra["plugin.nexus-publish.version"]}"
        id("io.github.sgtsilvio.gradle.defaults") version "${extra["plugin.defaults.version"]}"
        id("io.github.sgtsilvio.gradle.metadata") version "${extra["plugin.metadata.version"]}"
        id("io.github.sgtsilvio.gradle.javadoc-links") version "${extra["plugin.javadoc-links.version"]}"
        id("com.github.hierynomus.license") version "${extra["plugin.license.version"]}"
    }

    if (file("../hivemq/plugins").exists()) {
        includeBuild("../hivemq/plugins")
    }
}

rootProject.name = "hivemq-google-cloud-pubsub-extension-customization-sdk"

if (file("../hivemq-extension-sdk").exists()) {
    includeBuild("../hivemq-extension-sdk")
}
