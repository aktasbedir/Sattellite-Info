package com.bediraktas.satelliteinfo.data.datasource.file

import com.bediraktas.satelliteinfo.data.model.entity.SatelliteDetail
import com.bediraktas.satelliteinfo.data.model.response.Satellite
import com.bediraktas.satelliteinfo.data.model.response.SatellitePositionsList
import com.bediraktas.satelliteinfo.common.Resource


interface FileDataSource {
    suspend fun getSatelliteList(fileName: String): Resource<List<Satellite>?>
    suspend fun getSatelliteDetail(fileName: String): Resource<List<SatelliteDetail>?>
    suspend fun getSatellitePosition(fileName: String): Resource<SatellitePositionsList?>
}