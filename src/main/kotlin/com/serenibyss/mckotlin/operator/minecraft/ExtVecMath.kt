@file:Suppress("unused")
@file:JvmName("VecMathUtils")

package com.serenibyss.mckotlin.operator.minecraft

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i
import kotlin.math.acos
import kotlin.math.sqrt

// Vec3d

operator fun Vec3d.times(other: Vec3d): Vec3d = Vec3d(x * other.x, y * other.y, z * other.z)
operator fun Vec3d.times(other: Double): Vec3d = scale(other)
operator fun Vec3d.times(other: Float): Vec3d = this * other.toDouble()
operator fun Vec3d.times(other: Int): Vec3d = this * other.toDouble()

operator fun Vec3d.div(other: Vec3d): Vec3d = Vec3d(x / other.x, y / other.y, z / other.z)
operator fun Vec3d.div(other: Double): Vec3d = this * (1 / other)
operator fun Vec3d.div(other: Float): Vec3d = this / other.toDouble()
operator fun Vec3d.div(other: Int): Vec3d = this / other.toDouble()

operator fun Vec3d.plus(other: Vec3d): Vec3d = add(other)
operator fun Vec3d.minus(other: Vec3d): Vec3d = subtract(other)
operator fun Vec3d.unaryMinus(): Vec3d = this * -1.0

infix fun Vec3d.dot(other: Vec3d) = dotProduct(other)
infix fun Vec3d.cross(other: Vec3d): Vec3d = crossProduct(other)
infix fun Vec3d.angle(other: Vec3d) = acos((this dot other) / (length() * other.length()))

// Vec3i

fun Vec3i.lengthSquared() = x * x + y * y + z * z
fun Vec3i.length() = sqrt(lengthSquared().toDouble())

operator fun Vec3i.times(other: Vec3i): Vec3i = Vec3i(x * other.x, y * other.y, z * other.z)
operator fun Vec3i.times(other: Double): Vec3i = Vec3i(x * other, y * other, z * other)
operator fun Vec3i.times(other: Float): Vec3i = this * other.toDouble()
operator fun Vec3i.times(other: Int): Vec3i = this * other.toDouble()

operator fun Vec3i.div(other: Vec3i) = Vec3i(x / other.x, y / other.y, z / other.z)
operator fun Vec3i.div(other: Double): Vec3i = this * (1 / other)
operator fun Vec3i.div(other: Float): Vec3i = this / other.toDouble()
operator fun Vec3i.div(other: Int): Vec3i = this / other.toDouble()

operator fun Vec3i.plus(other: Vec3i): Vec3i = Vec3i(x + other.x, y + other.y, z + other.z)
operator fun Vec3i.minus(other: Vec3i): Vec3i = Vec3i(x - other.x, y - other.y, z - other.z)
operator fun Vec3i.unaryMinus(): Vec3i = this * -1

infix fun Vec3i.dot(other: Vec3i) = x * other.x + y * other.y + z * other.z
infix fun Vec3i.cross(other: Vec3i): Vec3i = crossProduct(other)
infix fun Vec3i.angle(other: Vec3i) = acos((this dot other) / (length() * other.length()))

// BlockPos

operator fun BlockPos.times(other: BlockPos) = BlockPos(x * other.x, y * other.y, z * other.z)
operator fun BlockPos.times(other: Vec3d) = BlockPos((x * other.x).toInt(), (y * other.y).toInt(), (z * other.z).toInt())
operator fun BlockPos.times(other: Number) = BlockPos((x * other.toDouble()).toInt(), (y * other.toDouble()).toInt(), (z * other.toDouble()).toInt())

operator fun BlockPos.div(other: BlockPos) = BlockPos(x / other.x, y / other.y, z / other.z)
operator fun BlockPos.div(other: Vec3d) = BlockPos((x / other.x).toInt(), (y / other.y).toInt(), (z / other.z).toInt())
operator fun BlockPos.div(other: Number) = BlockPos((x / other.toDouble()).toInt(), (y / other.toDouble()).toInt(), (z / other.toDouble()).toInt())

operator fun BlockPos.plus(other: BlockPos): BlockPos = add(other)
operator fun BlockPos.minus(other: BlockPos): BlockPos = subtract(other)
