package com.bediraktas.satelliteinfo.data.datasource.file

import android.content.Context
import com.bediraktas.satelliteinfo.data.model.entity.SatelliteDetail
import com.bediraktas.satelliteinfo.data.model.response.Satellite
import com.bediraktas.satelliteinfo.data.model.response.SatellitePositionsList
import com.bediraktas.satelliteinfo.util.Resource
import com.bediraktas.satelliteinfo.util.getListFromJson
import com.bediraktas.satelliteinfo.util.getObjectFromJson
import com.bediraktas.satelliteinfo.util.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FileDataSourceImpl @Inject constructor(
    private val gson: Gson,
    @ApplicationContext private val context: Context
) : FileDataSource {
    override suspend fun getSatelliteList(fileName: String): Resource<List<Satellite>?> {
        return context.getListFromJson(fileName, gson, object : TypeToken<List<Satellite>>() {}.type)
    }

    override suspend fun getSatelliteDetail(fileName: String): Resource<List<SatelliteDetail>?> {
        return context.getListFromJson(fileName, gson,object : TypeToken<List<SatelliteDetail>>() {}.type)
    }

    override suspend fun getSatellitePosition(fileName: String): Resource<SatellitePositionsList?> {
        return context.getObjectFromJson(fileName, gson)
    }
}