package com.sql.utils

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

object DependencyInjectionContainer {
    private val dependencies = mutableMapOf<String, Any>()

    fun <T: Any> register(instance: T) {
        val className = instance::class.simpleName!!
        dependencies[className] = instance
    }

    fun <T: Any> register(customName: String, instance: T) {
        dependencies[customName] = instance
    }

    fun <T: Any> register(vararg instances: T) = instances.forEach { instance -> register(instance) }

    @Suppress("UNCHECKED_CAST")
    fun <T: Any> get(type: KClass<T>): T? = dependencies[type.simpleName!!] as T?
}

class InjectionDelegate<T: Any>(private val type: KClass<T>) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        DependencyInjectionContainer.get(type) ?: throw IllegalStateException("${property.name} is not initialized")
}

inline fun <reified T: Any> inject(): InjectionDelegate<T> = InjectionDelegate(T::class)