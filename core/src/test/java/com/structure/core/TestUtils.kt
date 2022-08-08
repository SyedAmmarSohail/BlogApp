package com.structure.core


fun <T : Enum<T>> randomEnumValue(enumClass: Class<T>, except: List<T> = listOf()): T {
    val enumValues = enumValues(enumClass, except)
    val randInt = (enumValues.indices).random()
    return enumValues[randInt]
}

private fun <T : Enum<T>> enumValues(enumClass: Class<T>, except: List<T>): List<T> {
    val exceptSet = except.toSet()
    return enumClass.enumConstants
        .filter { !exceptSet.contains(it) }
}
