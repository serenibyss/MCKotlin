package com.serenibyss.mckotlin.operator.minecraftforge

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandlerModifiable

operator fun IItemHandlerModifiable.get(index: Int): ItemStack = getStackInSlot(index)

operator fun IItemHandlerModifiable.set(index: Int, stack: ItemStack) = setStackInSlot(index, stack)

operator fun IItemHandlerModifiable.iterator(): Iterator<ItemStack> =
    Array(slots) { getStackInSlot(it) }.iterator()
