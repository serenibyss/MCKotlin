@file:Suppress("unused")
@file:JvmName("NBTUtils")

package com.serenibyss.mckotlin.operator.minecraft

import net.minecraft.nbt.*
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.*

/*
 * Allow Kotlin array-like syntax for NBTTagCompound setter
 * ex:
 * val tag = NBTTagCompound()
 * tag["x"] = 400
 * val x = tag.getInteger("x")
 */
operator fun <T> NBTTagCompound.set(key: String, value: T) = when (value) {
    is Boolean -> setBoolean(key, value)
    is Byte -> setByte(key, value)
    is ByteArray -> setByteArray(key, value)
    is Double -> setDouble(key, value)
    is Float -> setFloat(key, value)
    is IntArray -> setIntArray(key, value)
    is Int -> setInteger(key, value)
    is Long -> setLong(key, value)
    is Short -> setShort(key, value)
    is String -> setString(key, value)
    is NBTBase -> setTag(key, value)
    is UUID -> setUniqueId(key, value)
    else -> throw Exception("unsupported type")
}

val tagTypes = arrayListOf(
    Byte::class.java to 1,
    Short::class.java to 2,
    Int::class.java to 3,
    Long::class.java to 4,
    Float::class.java to 5,
    Double::class.java to 6,
    ByteArray::class.java to 7,
    String::class.java to 8,
    NBTTagList::class.java to 9,
    NBTTagCompound::class.java to 10,
    IntArray::class.java to 11
)

/*
 * Allow Kotlin array-like syntax for NBTTagCompound getter
 * ex:
 * val tag = NBTTagCompound()
 * tag.setInteger("x", 400)
 * val x = tag["x"]
 */
inline operator fun <reified T> NBTTagCompound.get(key: String): T {
    if (!hasKey(key)) {
        throw IllegalArgumentException("Could not get value of $key as it does not exist")
    }

    return when (getTagId(key).toInt()) {
        1 -> getByte(key) as T
        2 -> getShort(key) as T
        3 -> getInteger(key) as T
        4 -> getLong(key) as T
        5 -> getFloat(key) as T
        6 -> getDouble(key) as T
        7 -> getByteArray(key) as T
        8 -> getString(key) as T
        9 -> getTagList(key, tagTypes.find { it.first == T::class.java }?.second ?: 0) as T
        10 -> getCompoundTag(key) as T
        11 -> getIntArray(key) as T
        else -> throw Exception("unsupported type")
    }
}

inline fun <reified T> NBTTagCompound.getList(name: String): List<T> {
    val c = T::class.java
    val tagList = getTagList(name, tagTypes.find { it.first == c}?.second ?: throw Exception("unsupported type"))
    if (tagList.tagCount() == 0) return emptyList()
    return Array(tagList.tagCount()) {
        when (T::class.java) {
            Int::class.java -> tagList.getIntAt(it) as T
            IntArray::class.java -> tagList.getIntArrayAt(it) as T
            Double::class.java -> tagList.getDoubleAt(it) as T
            Float::class.java -> tagList.getFloatAt(it) as T
            String::class.java -> tagList.getStringTagAt(it) as T
            NBTTagCompound::class.java -> tagList.getCompoundTagAt(it) as T
            else -> throw Exception("unsupported type")
        }
    }.toList()
}

inline fun <reified T> NBTTagCompound.setList(name: String, list: List<T>) {
    setTag(name, NBTTagList().apply {
        list.map {
            when (T::class.java) {
                Int::class.java -> NBTTagInt(it as Int)
                IntArray::class.java -> NBTTagIntArray(it as IntArray)
                Double::class.java -> NBTTagDouble(it as Double)
                Float::class.java -> NBTTagFloat(it as Float)
                String::class.java -> NBTTagString(it as String)
                NBTTagCompound::class.java -> it as NBTTagCompound
                else -> throw Exception("unsupported type")
            }
        }.forEach(::appendTag)
    })
}

/*
 * val tag = NBTTagCompound()
 * if ("x" in tag) {
 *   // ...
 * } else {
 *   // ...
 * }
 */
operator fun NBTTagCompound.contains(key: String) = hasKey(key)

operator fun NBTTagCompound.iterator() =
    object : Iterator<Pair<String, NBTBase>> {
        val keys = this@iterator.keySet.iterator()
        override fun hasNext() = keys.hasNext()
        override fun next(): Pair<String, NBTBase> {
            val next = keys.next()
            return next to this@iterator[next]
        }
    }

val NBTTagList.size
    get() = tagCount()

val NBTTagList.indices
    get() = 0 until size
