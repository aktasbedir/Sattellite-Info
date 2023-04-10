package com.bediraktas.satelliteinfo.domain.repository

import com.bediraktas.satelliteinfo.data.model.entity.SatelliteDetail
import com.bediraktas.satelliteinfo.data.model.entity.SatellitePosition
import com.bediraktas.satelliteinfo.data.model.response.Satellite
import com.bediraktas.satelliteinfo.util.Resource


interface SatelliteRepository {
    suspend fun getSatellites(fileName: String): Resource<List<Satellite>?>

    suspend fun getSatelliteDetail(
        fileName: String,
        satelliteId: Int
    ): Resource<List<SatelliteDetail?>?>

    suspend fun getSatellitePositions(
        fileName: String,
        satelliteId: Int
    ): Resource<List<SatellitePosition?>?>

    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetail)

    suspend fun insertSatellitePosition(satellitePosition: SatellitePosition)
}