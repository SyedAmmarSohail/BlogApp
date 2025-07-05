// Top-level build file where you can add configuration options common to all sub-projects/modules.

// Remove plugins block from root build.gradle.kts

// You can keep buildscript or dependency management here if needed.

buildscript {
    repositories {
        google()
        mavenCentral()
//        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.7.3")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.54")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

// Configure JVM toolchain for all tasks in the project
allprojects {
    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.add("-source")
        options.compilerArgs.add("21")
        options.compilerArgs.add("-target")
        options.compilerArgs.add("21")
    }
    
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "21"
        }
    }
}