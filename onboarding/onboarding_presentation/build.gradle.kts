apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))

    "implementation"(ViewPager.viewPager)
    "implementation"(ViewPager.viewPagerIndicator)
}