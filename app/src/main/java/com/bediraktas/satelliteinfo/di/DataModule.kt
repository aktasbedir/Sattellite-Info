package com.bediraktas.satelliteinfo.di

import android.content.Context
import androidx.room.Room
import com.bediraktas.satelliteinfo.data.datasource.file.FileDataSource
import com.bediraktas.satelliteinfo.data.datasource.file.FileDataSourceImpl
import com.bediraktas.satelliteinfo.data.datasource.local.RoomDataSource
import com.bediraktas.satelliteinfo.data.datasource.local.RoomDataSourceImpl
import com.bediraktas.satelliteinfo.data.db.Converters
import com.bediraktas.satelliteinfo.data.db.SatelliteDao
import com.bediraktas.satelliteinfo.data.db.SatelliteDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideFileDataSource(
        gson: Gson,
        @ApplicationContext appContext: Context,
    ): FileDataSource = FileDataSourceImpl(gson, appContext)

    @Provides
    @Singleton
    fun provideRoomDataSource(dao: SatelliteDao): RoomDataSource = RoomDataSourceImpl(dao)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context,
        typeConverters: Converters
    ): SatelliteDatabase {
        return Room
            .databaseBuilder(appContext, SatelliteDatabase::class.java, "satellite.db")
            .addTypeConverter(typeConverters)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideSatelliteDao(db: SatelliteDatabase): SatelliteDao {
        return db.getSatelliteDao()
    }
}