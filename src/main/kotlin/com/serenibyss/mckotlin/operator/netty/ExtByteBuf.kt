@file:Suppress("unused")
@file:JvmName("ByteBufUtils")

package com.serenibyss.mckotlin.operator.netty

import io.netty.buffer.ByteBuf
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.fml.common.network.ByteBufUtils

fun ByteBuf.writeString(value: String) = ByteBufUtils.writeUTF8String(this, value)
fun ByteBuf.readString(): String = ByteBufUtils.readUTF8String(this)

fun ByteBuf.writeItemStack(value: ItemStack) = ByteBufUtils.writeItemStack(this, value)
fun ByteBuf.readItemStack(): ItemStack = ByteBufUtils.readItemStack(this)

fun ByteBuf.writeTag(value: NBTTagCompound) = ByteBufUtils.writeTag(this, value)
fun ByteBuf.readTag(): NBTTagCompound? = ByteBufUtils.readTag(this)

fun ByteBuf.writeFluidStack(value: FluidStack) {
    writeString(FluidRegistry.getFluidName(value.fluid))
    writeInt(value.amount)
    if (value.tag != null) {
        writeBoolean(true)
        writeTag(value.tag)
    } else writeBoolean(false)
}

fun ByteBuf.readFluidStack(): FluidStack? {
    val fluid = FluidRegistry.getFluid(readString())
    return if (fluid != null) {
        val amount = readInt()
        if (readBoolean()) FluidStack(fluid, amount, readTag()) else FluidStack(fluid, amount)
    } else null
}
