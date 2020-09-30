package dependencies

import Versions.coroutines_version
import Versions.okhttp_version

object Dependencies {
    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    val kotlin_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    val kotlin_coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val navigation_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.nav_components}"
    val navigation_runtime = "androidx.navigation:navigation-runtime:${Versions.nav_components}"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.nav_components}"
    val navigation_dynamic =
        "androidx.navigation:navigation-dynamic-features-fragment:${Versions.nav_components}"
    val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle_version}"
    val lifecycle_coroutines =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_version}"
    val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    val okhttp = "com.squareup.okhttp3:okhttp:$okhttp_version"
    val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
}