@file:Suppress("PackageDirectoryMismatch")

object Configs {
    const val compileSdkVersion = 33
    const val applicationId = "com.bediraktas.satelliteinfo"
    const val minSdkVersion = 24
    const val targetSdkVersion = 33
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

}

object ClassPaths {
    const val gradle = "com.android.tools.build:gradle:${`Version Catalog`.gradleVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${`Version Catalog`.kotlinVersion}"
}