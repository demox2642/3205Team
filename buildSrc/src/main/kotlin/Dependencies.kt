import kotlin.reflect.full.memberProperties

object AndroidX {
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.androidX}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.androidX}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.androidX}"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.androidX}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.androidX}"
    const val core_ktx = "androidx.core:core-ktx:1.7.0"
    const val appcompat = "androidx.appcompat:appcompat:1.4.1"
    const val activity = "androidx.activity:activity-compose:1.3.1"
    const val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.androidX}"
    const val workRuntime = "androidx.work:work-runtime-ktx:${Versions.workVersion}"
    const val composeConstraint = "androidx.constraintlayout:constraintlayout-compose:${Versions.condtraint}"
    const val paging = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
    const val pagingCompose = "androidx.paging:paging-compose:3.3.0-beta01"

    fun getAll() =
        AndroidX::class.memberProperties
            .filter { it.isConst }
            .map { it.getter.call().toString() }
            .toSet()
}

object Di {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltTestUnit = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
}

object Accompanist {
    const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    const val flowLayout = "com.google.accompanist:accompanist-flowlayout:${Versions.accompanist}"
    const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    const val viewPager = "com.google.accompanist:accompanist-pager:0.18.0"
}

object Navigation {
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigationVersion}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0"

    fun getAll() =
        Navigation::class.memberProperties
            .filter { it.isConst }
            .map { it.getter.call().toString() }
            .toSet()
}

object Network {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val simplexml = "com.squareup.retrofit2:converter-simplexml:2.5.0"
    const val retrofitConvertor = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLoggerVersion}"
    const val kotlinSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0"

    fun getAll() =
        Network::class.memberProperties
            .filter { it.isConst }
            .map { it.getter.call().toString() }
            .toSet()
}

object Coroutines {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutinesCore}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutinesAndroidVersion}"
    const val coroutinesJvm = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.kotlinCoroutines}"
    const val viewmodelKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtxVersion}"

    fun getAll() =
        Coroutines::class.memberProperties
            .filter { it.isConst }
            .map { it.getter.call().toString() }
            .toSet()
}

object Room {
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomKTX = "androidx.room:room-ktx:${Versions.room}"
    const val roomPaging = "androidx.room:room-paging:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
}

object Coil {
    const val coil = "io.coil-kt:coil-compose:2.2.1"
    const val svg = "io.coil-kt:coil-svg:2.0.0"
}

object GlideImage {
    const val glide = "com.github.skydoves:landscapist-glide:2.3.3"
}

object Firebase {
    const val fb_bom = "com.google.firebase:firebase-bom:30.4.1"
    const val fb_db = "com.google.firebase:firebase-database-ktx:20.0.6"
    const val fb_conf = "com.google.firebase:firebase-config-ktx:21.1.2"

    fun getAll() =
        Coroutines::class.memberProperties
            .filter { it.isConst }
            .map { it.getter.call().toString() }
            .toSet()
}
