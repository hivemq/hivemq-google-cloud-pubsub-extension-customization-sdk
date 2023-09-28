pluginManagement {
    if (file("../hivemq/plugins").exists()) {
        includeBuild("../hivemq/plugins")
    }
}

rootProject.name = "hivemq-google-cloud-pubsub-extension-customization-sdk"

if (file("../hivemq-extension-sdk").exists()) {
    includeBuild("../hivemq-extension-sdk")
}
