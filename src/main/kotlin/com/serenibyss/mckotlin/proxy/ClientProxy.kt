package com.serenibyss.mckotlin.proxy

import com.serenibyss.mckotlin.MCKotlin
import net.minecraft.client.resources.I18n

class ClientProxy: CommonProxy() {

    override fun localize(key: String, vararg args: Any): String = I18n.format(key, args)

    override fun preInit() {
        super.preInit()
        MCKotlin.logger.info("Initialized Client")
    }
}
