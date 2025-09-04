plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)        // ✅ Hilt plugin
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.codestream.tetrak"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.codestream.tetrak"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("release/tetrak-keystore.jks")
            storePassword = "notesaver123"
            keyAlias = "keyAlias"
            keyPassword = "notesaver123"
        }
    }

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
    hilt {
        enableAggregatingTask = false
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // --- Room ---
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)        // Coroutines + Flow
    ksp(libs.androidx.room.compiler)              // Use KSP for annotation processing
    // annotationProcessor(libs.androidx.room.compiler) // if Java-only

    // --- Hilt ---
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler) // ⚠️ Hilt still requires KAPT, not KSP yet
    kapt(libs.javapoet)

    // Optional
    implementation(libs.androidx.room.paging)     // Paging 3 support
    testImplementation(libs.androidx.room.testing)

    constraints {
        implementation("com.squareup:javapoet:1.13.0") {
            because("Avoid older transitive versions that lack ClassName.canonicalName()")
        }
    }
}

// ---- APK naming ----
val appName = "Tetrak"

androidComponents {
    onVariants { variant ->
        variant.outputs.forEach { output ->
            val computedName = providers.provider {
                val vName = output.versionName.orNull ?: "1.0"
                val vCode = output.versionCode.orNull ?: 1
                val buildType = variant.buildType
                "$appName $vName ($vCode)_$buildType.apk"
            }

//            output.outputFileName.set(computedName)
        }
    }
}
