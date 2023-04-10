package com.bediraktas.satelliteinfo.util

import android.content.Context
import com.bediraktas.satelliteinfo.util.Constant.IO_ERROR
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type

fun inputStreamToString(inputStream: InputStream): String {
    return try {
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes, 0, bytes.size)
        String(bytes)
    } catch (e: IOException) {
        "IO_ERROR"
    }
}

inline fun <reified T> Context.getListFromJson(
    jsonFileName: String,
    gson: Gson,
    listType: Type
): Result<T> {
    val myJson = inputStreamToString(this.assets.open(jsonFileName))
    return if (myJson == IO_ERROR) Result.Error(IO_ERROR)
    else Result.Success(gson.fromJson<T>(myJson, listType))
}

inline fun <reified T> Context.getObjectFromJson(jsonFileName: String, gson: Gson): Result<T> {
    val myJson = inputStreamToString(this.assets.open(jsonFileName))
    return if (myJson == IO_ERROR) Result.Error(IO_ERROR)
    else Result.Success(gson.fromJson(myJson, T::class.java))
}

