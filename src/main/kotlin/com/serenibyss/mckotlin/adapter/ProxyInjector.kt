package com.serenibyss.mckotlin.adapter

import net.minecraftforge.fml.common.FMLCommonHandler
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ProxyInjector<out E: Any>(private val client: KClass<out E>,
                                private val server: KClass<E>): ReadOnlyProperty<Any, E> {

    private val proxy: E by lazy {
        val clazz = if (FMLCommonHandler.instance().side.isServer) server else client
        clazz.objectInstance ?: clazz.java.getConstructor().newInstance()
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): E = proxy
}
