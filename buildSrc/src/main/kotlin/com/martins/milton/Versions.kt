package com.martins.milton

object Versions {

    object DefaultConfig {
        const val compileSdk = 30
        const val buildTools = "30.0.3"
        const val applicationId = "com.martins.milton.goktest"
        const val minSdk = 21
        const val targetSdk = 30
        val versionCode = VersionCode.getVersionCode()
        val versionName = VersionCode.getVersionName()

        object VersionCode {
            private const val MAJOR = 0
            private const val MINOR = 0
            private const val PATCH = 1
            private const val HOTFIX = 0

            fun getVersionCode() = MAJOR * 10000000 + MINOR * 100000 + PATCH * 100 + HOTFIX
            fun getVersionName() = "$MAJOR.$MINOR.$PATCH"
        }
    }

    object Core {
        const val java = "1.8"
        const val kotlin = "1.4.21"
        const val buildToolsGradle = "4.1.2"
        const val kotlinCore = "1.3.2"
        const val coroutines = "1.3.9"
        const val coroutinesViewModel = "2.2.0"
    }

    object UI {
        const val appCompat = "1.2.0"
        const val material = "1.2.1"
        const val constraint = "2.0.4"
        const val swipeRefresh = "1.1.0"
        const val navigationComponent = "2.3.3"
        const val glide = "4.12.0"
    }

    object Network {
        const val retrofit = "2.9.0"
        const val gson = "2.8.6"
        const val retrofitGson = "2.8.1"
        const val httpLogInterceptor = "3.12.0"
    }

    object DI {
        const val dagger = "2.32"
    }

    object Test {
        const val junit = "4.13.1"
        const val testCore = "2.1.0"
        const val mockito = "3.3.3"
        const val mockitoKotlin = "2.2.0"
    }

}