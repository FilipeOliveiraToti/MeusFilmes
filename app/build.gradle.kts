plugins {
    kotlin("kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.foliveira.pacientes.meusfilmes"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.foliveira.pacientes.meusfilmes"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)

    //Glide - Utilizada por carregar a ImageView com a imagem salva em uma URL
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //ROOM - Utilizado para criar / gerenciar o banco de dados local
    implementation("androidx.room:room-ktx:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    //ViewModel lifecycle - Permite utilizar o "viewModelScope" dentro do ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")

    //Koin - Lib para gerenciar a injeção de dependências
    implementation("io.insert-koin:koin-android:4.0.0")

    //Retrofit - Permite realizar requests em APIs
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    //GSON Converter - Utilizado para serializar / deserializar JSON. Necessário para o Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}