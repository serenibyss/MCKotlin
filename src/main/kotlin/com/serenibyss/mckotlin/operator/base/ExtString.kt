@file:Suppress("unused")
@file:JvmName("StringUtils")

package com.serenibyss.mckotlin.operator.base

import com.serenibyss.mckotlin.MCKotlin

fun String.localize(vararg args: Any): String = MCKotlin.proxy.localize(this, args)

