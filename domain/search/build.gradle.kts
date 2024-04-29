plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    implementation(Coroutines.coroutinesAndroid)
//    implementation(AndroidX.paging)
//    implementation(AndroidX.pagingCompose)
    implementation(libs.androidx.paging.common.ktx)
}
