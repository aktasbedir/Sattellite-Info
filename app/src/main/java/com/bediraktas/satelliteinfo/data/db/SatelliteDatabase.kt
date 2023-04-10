package com.bediraktas.satelliteinfo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bediraktas.satelliteinfo.data.model.entity.SatelliteDetail
import com.bediraktas.satelliteinfo.data.model.entity.SatellitePosition

@Database(
    entities = [SatelliteDetail::class, SatellitePosition::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SatelliteDatabase : RoomDatabase() {
    abstract fun getSatelliteDao(): SatelliteDao
}