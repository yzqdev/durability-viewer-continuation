pluginManagement {
  repositories {

    maven("https://maven.neoforged.net/releases")

    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
//  repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)


}
rootProject.name="durability-viewer-continuation"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":DurabilityViewer")


