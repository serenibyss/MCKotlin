@file:Suppress("unused")
@file:JvmName("IInventoryUtils")

package com.serenibyss.mckotlin.operator.minecraft

import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack

operator fun IInventory.get(index: Int): ItemStack = getStackInSlot(index)

operator fun IInventory.set(index: Int, stack: ItemStack) = setInventorySlotContents(index, stack)

operator fun IInventory.iterator(): Iterator<ItemStack> =
    Array(sizeInventory) { getStackInSlot(it) }.iterator()
