package com.bediraktas.satelliteinfo.data.datasource.local


import com.bediraktas.satelliteinfo.data.model.entity.SatelliteDetail
import com.bediraktas.satelliteinfo.data.model.entity.SatellitePosition
import com.bediraktas.satelliteinfo.util.Resource

interface RoomDataSource {
    suspend fun getSatelliteDetails(): Resource<List<SatelliteDetail>?>
    suspend fun getSatellitePositions(): Resource<List<SatellitePosition>?>
    suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetail)
    suspend fun insertSatellitePosition(satellitePosition: SatellitePosition)
    suspend fun deleteSatelliteDetail(satelliteDetail: SatelliteDetail)
    suspend fun deleteSatellitePosition(satellitePosition: SatellitePosition)
}