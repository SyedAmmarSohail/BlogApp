package com.structure.blog_data.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Indicates that the annotated enum has a fallback value. The fallback must be set via
 * [.name]. If no enum constant with the provided name is declared in the annotated
 * enum type an [assertion error][AssertionError] will be thrown.
 *
 *
 * To leverage from [FallbackEnum] [FallbackEnum.ADAPTER_FACTORY] must be added to
 * your [moshi instance][Moshi]:
 *
 * <pre>`
 * Moshi moshi = new Moshi.Builder()
 * .add(FallbackEnum.ADAPTER_FACTORY)
 * .build();
`</pre> *
 */
internal object FallbackEnum {

    const val ENUM_UNKNOWN = "UNKNOWN"

    /** Builds an adapter that can process enums annotated with [FallbackEnum].  */
    var ADAPTER_FACTORY =
        JsonAdapter.Factory { type, annotations, moshi ->

            val rawType = Types.getRawType(type)
            if (rawType.isEnum) {
                return@Factory FallbackEnumJsonAdapter(
                    rawType as Class<out Enum<*>?>,
                    ENUM_UNKNOWN
                ).nullSafe()
            }
            null
        }
}
