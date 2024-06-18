package com.api.pladder.core.utils.entity

import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


/*
* 리플렉션으로 인한 성능 이슈 가능성 있음
* 해당 메서드 사용을 최소화하고, 필요한 경우에만 사용할 것
* */
object EntityUpdateUtil {
    inline fun <reified T : Any> updateEntity(entity: T, request: T) {
        val entityClass = T::class

        for (prop in entityClass.memberProperties) {
            prop.isAccessible = true

            val requestValue = prop.get(request)
            if (requestValue != null) {
                when (requestValue) {
                    is Int -> if (requestValue == 0) continue
                    is Double -> if (requestValue == 0.0) continue
                    is Float -> if (requestValue == 0.0f) continue
                    is Long -> if (requestValue == 0L) continue
                    is Boolean -> if (!requestValue) continue
                    is Enum<*> -> if (requestValue.name == "UNKNOWN") continue
                }

                val mutableProp = prop as? kotlin.reflect.KMutableProperty1<T, Any?>
                mutableProp?.set(entity, requestValue)
            }
        }
    }
}
