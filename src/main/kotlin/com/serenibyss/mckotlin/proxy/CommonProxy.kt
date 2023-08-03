package com.serenibyss.mckotlin.proxy

import com.serenibyss.mckotlin.MCKotlin
import net.minecraft.util.text.translation.I18n

open class CommonProxy {

    open fun localize(key: String, vararg args: Any): String = I18n.translateToLocalFormatted(key, args)

    open fun preInit() {
        MCKotlin.logger.info("Initialized Server")
    }
}
