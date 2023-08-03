@file:Suppress("unused")
@file:JvmName("StringUtils")

package com.serenibyss.mckotlin.operator.base

import com.serenibyss.mckotlin.MCKotlin
import net.minecraft.util.text.TextComponentString
import net.minecraft.util.text.TextComponentTranslation
import net.minecraft.util.text.TextFormatting

fun String.canLocalize() = MCKotlin.proxy.canLocalize(this)

fun String.localize(vararg args: Any): String = MCKotlin.proxy.localize(this, *args)

fun String.toComponent() = TextComponentString(this)

fun String.toComponentTranslation(vararg args: Any) = TextComponentTranslation(this, *args)

operator fun TextFormatting.plus(str: String) = "$this$str"
operator fun TextFormatting.plus(other: TextFormatting) = "$this$other"
