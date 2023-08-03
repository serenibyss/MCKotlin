package com.serenibyss.mckotlin

import com.serenibyss.mckotlin.adapter.ProxyInjector
import com.serenibyss.mckotlin.proxy.ClientProxy
import com.serenibyss.mckotlin.proxy.CommonProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.Logger

const val ADAPTER = "com.serenibyss.mckotlin.adapter.KotlinAdapter"

@Mod(
    modid = MCKTags.MODID,
    name = MCKTags.MODNAME,
    version = MCKTags.VERSION,
    acceptedMinecraftVersions = "[1.12.2]",
    modLanguageAdapter = ADAPTER
)
class MCKotlin {

    companion object {
        lateinit var logger: Logger

        val proxy by ProxyInjector(ClientProxy::class, CommonProxy::class)
    }

    @EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        logger = event.modLog
        proxy.preInit()
    }
}
