apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.blogDomain))

    "implementation"(Coil.coilCompose)

    "implementation"(ViewPager.viewPager)
    "implementation"(ViewPager.viewPagerIndicator)

}