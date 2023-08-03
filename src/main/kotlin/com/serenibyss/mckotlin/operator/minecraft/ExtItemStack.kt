@file:Suppress("unused")
@file:JvmName("ItemStackUtils")

package com.serenibyss.mckotlin.operator.minecraft

import net.minecraft.block.Block
import net.minecraft.inventory.IInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandlerModifiable

val ItemStack.toolClasses: Set<String>
    get() = item.getToolClasses(this)

val ItemStack.isNotEmpty
    get() = !this.isEmpty

fun Item.toStack(amount: Int = 1, meta: Int = 0) = ItemStack(this, amount, meta)

fun Block.toStack(amount: Int = 1, meta: Int = 0) = ItemStack(this, amount, meta)


// IItemHandlerModifiable

val IItemHandlerModifiable.size: Int
    get() = slots

operator fun IItemHandlerModifiable.get(index: Int): ItemStack = getStackInSlot(index)

operator fun IItemHandlerModifiable.set(index: Int, stack: ItemStack) = setStackInSlot(index, stack)

operator fun IItemHandlerModifiable.iterator(): Iterator<ItemStack> =
    object : Iterator<ItemStack> {
        var index = 0
        override fun hasNext() = index < this@iterator.size
        override fun next() = this@iterator[index++]
    }


// IInventory

val IInventory.size: Int
    get() = sizeInventory

operator fun IInventory.get(index: Int): ItemStack = getStackInSlot(index)

operator fun IInventory.set(index: Int, stack: ItemStack) = setInventorySlotContents(index, stack)

operator fun IInventory.iterator(): Iterator<ItemStack> =
    object : Iterator<ItemStack> {
        var index = 0
        override fun hasNext() = index < this@iterator.size
        override fun next() = this@iterator[index++]
    }


