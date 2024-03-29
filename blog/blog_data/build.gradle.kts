apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.blogDomain))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Moshi.moshiKotlin)
    "implementation"(Moshi.moshi)

    "kapt"(Moshi.moshiCodegen)

    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}