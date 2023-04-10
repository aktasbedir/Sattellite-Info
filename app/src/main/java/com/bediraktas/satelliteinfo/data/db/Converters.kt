package com.bediraktas.satelliteinfo.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.bediraktas.satelliteinfo.data.model.response.PositionCoordinate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


@ProvidedTypeConverter
class Converters @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun fromString(value: String?): List<PositionCoordinate?> {
        val listType = object :
            TypeToken<ArrayList<PositionCoordinate?>?>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<PositionCoordinate?>?): String {
        return gson.toJson(list)
    }
}