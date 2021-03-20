package com.martins.milton

object Dependencies {

    const val buildToolsGradle = "com.android.tools.build:gradle:${Versions.Core.buildToolsGradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Core.kotlin}"

    object Core {
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Core.kotlin}"
        const val kotlinCore = "androidx.core:core-ktx:${Versions.Core.kotlinCore}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Core.coroutines}"
        const val coroutinesViewModel ="androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Core.coroutinesViewModel}"
    }

    object UI {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.UI.appCompat}"
        const val material = "com.google.android.material:material:${Versions.UI.material}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.UI.constraint}"
        const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.UI.swipeRefresh}"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.UI.navigationComponent}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.UI.navigationComponent}"
        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.UI.navigationComponent}"
        const val glide = "com.github.bumptech.glide:glide:${Versions.UI.glide}"
        const val glideProcessor = "com.github.bumptech.glide:compiler:${Versions.UI.glide}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        const val gson = "com.google.code.gson:gson:${Versions.Network.gson}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.Network.retrofitGson}"
        const val httpLogInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.httpLogInterceptor}"
    }

    object DI {
        const val dagger = "com.google.dagger:dagger:${Versions.DI.dagger}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.DI.dagger}"
        const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.DI.dagger}"
        const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.DI.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.DI.dagger}"
    }

    object Tests {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val testCore = "androidx.arch.core:core-testing:${Versions.Test.testCore}"
        const val mockito = "org.mockito:mockito-core:${Versions.Test.mockito}"
        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.Test.mockitoKotlin}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Core.coroutines}"
        const val coroutinesAndroidTest = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Core.coroutines}"
    }

}