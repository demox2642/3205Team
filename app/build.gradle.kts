plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.a3205team"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.a3205team"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(Modules.Data.HISTORY))
    implementation(project(Modules.Data.DATABASE))
    implementation(project(Modules.Data.SEARCH))
    implementation(project(Modules.Presentation.BASE_UI))
    implementation(project(Modules.Presentation.HISTORY))
    implementation(project(Modules.Presentation.SEARCH))
    implementation(project(Modules.Domain.HISTORY))
    implementation(project(Modules.Domain.SEARCH))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(Navigation.composeNavigation)
    implementation(Navigation.hiltNavigation)
    implementation(AndroidX.composeConstraint)
    uiImplentation()
    domainNetworkImplentation()
    implementation(Network.loggingInterceptor)
    implementation(Room.room)
    implementation(Room.roomKTX)
    implementation(Room.roomPaging)
    kapt(Room.roomCompiler)
    implementation("androidx.core:core-splashscreen:1.0.1")
}
