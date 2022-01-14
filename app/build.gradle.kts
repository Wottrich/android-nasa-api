plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
}

android {
    compileSdk = AndroidSdk.targetSdk
    buildToolsVersion = AndroidSdk.buildToolsVersion

    defaultConfig {
        applicationId = "github.io.wottrich.nasaapis"
        minSdk = AndroidSdk.minSdk
        targetSdk = AndroidSdk.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = AndroidSdk.javaVersion
        targetCompatibility = AndroidSdk.javaVersion
    }
    kotlinOptions {
        jvmTarget = AndroidSdk.javaVersion.toString()
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }
    packagingOptions {
        resources.excludes += "META-INF/*"
    }
}

dependencies {

    //Kotlin
    implementation(Libs.kotlinStdlib)
    implementation(Libs.androidCoreKtx)
    implementation(Libs.appCompat)
    implementation(Libs.androidMaterial)
    coroutines()

    //Koin
    koin()

    //Modules
    moduleDatasource()
    moduleUiHome()
    moduleUiAPOD()
    moduleUiEPIC()
    moduleCommonAndroid()
    moduleDomain()

    //Compose
    composeUi()
    implementation(Libs.composeNavigation)
    implementation(Libs.composeNavigationAnimationAccompanist)
    moduleCommonUiCompose()

    //File
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Test
    unitTest()
    instrumentalTest()
}