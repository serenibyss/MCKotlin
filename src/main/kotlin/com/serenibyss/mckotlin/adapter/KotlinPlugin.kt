package com.serenibyss.mckotlin.adapter

import net.minecraftforge.common.ForgeVersion
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.Name("MCKotlin")
class KotlinPlugin: IFMLLoadingPlugin {

    override fun getASMTransformerClass() = arrayOfNulls<String?>(0)

    override fun getModContainerClass() = null

    override fun getSetupClass() = "com.serenibyss.mckotlin.adapter.KotlinCallHook"

    override fun injectData(data: Map<String, Any>?) = Unit

    override fun getAccessTransformerClass() = null
}
