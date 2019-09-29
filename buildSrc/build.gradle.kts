plugins {
    `kotlin-dsl`
}
repositories {
    google()
    jcenter()
}

dependencies {
    implementation("com.android.tools.build.jetifier:jetifier-processor:1.0.0-beta07")
    implementation(gradleApi())
    implementation(localGroovy())
}