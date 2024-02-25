apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Moshi.moshiKotlin)
}