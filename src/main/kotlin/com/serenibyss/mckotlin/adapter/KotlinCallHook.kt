package com.serenibyss.mckotlin.adapter

import net.minecraftforge.fml.relauncher.IFMLCallHook

@Suppress("unused")
class KotlinCallHook: IFMLCallHook {

    override fun injectData(data: Map<String, Any>?) {
        val loader = data?.get("classLoader") as ClassLoader?
        try {
            loader?.loadClass("com.serenibyss.mckotlin.adapter.KotlinAdapter")
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Couldn't find MCKotlin language adapter!")
        }
    }

    override fun call() = null
}
