package com.bediraktas.satelliteinfo.data.datasource.local

import com.bediraktas.satelliteinfo.data.db.SatelliteDao
import com.bediraktas.satelliteinfo.data.model.entity.SatelliteDetail
import com.bediraktas.satelliteinfo.data.model.entity.SatellitePosition
import com.bediraktas.satelliteinfo.common.Constant.LIST_EMPTY_ERROR
import com.bediraktas.satelliteinfo.common.Resource
import javax.inject.Inject

class RoomDataSourceImpl @Inject constructor(private val dao: SatelliteDao) : RoomDataSource {
    override suspend fun getSatelliteDetails(): Resource<List<SatelliteDetail>?> {
        val satelliteDetails = dao.getSatelliteDetails()
        return if (satelliteDetails?.isNotEmpty() == true) {
            Resource.Success(satelliteDetails)
        } else Resource.Failure(LIST_EMPTY_ERROR)
    }

    override suspend fun getSatellitePositions(): Resource<List<SatellitePosition>?> {
        val satellitePositions = dao.getSatellitePositions()
        return if (satellitePositions?.isNotEmpty() == true) {
            Resource.Success(satellitePositions)
        } else Resource.Failure(LIST_EMPTY_ERROR)
    }

    override suspend fun insertSatelliteDetail(satelliteDetail: SatelliteDetail) {
        dao.insertSatelliteDetail(satelliteDetail)
    }

    override suspend fun insertSatellitePosition(satellitePosition: SatellitePosition) {
        dao.deleteSatellitePosition(satellitePosition)
        dao.insertSatellitePosition(satellitePosition)
    }

    override suspend fun deleteSatelliteDetail(satelliteDetail: SatelliteDetail) {
        dao.deleteSatelliteDetail(satelliteDetail)
    }

    override suspend fun deleteSatellitePosition(satellitePosition: SatellitePosition) {
        dao.deleteSatellitePosition(satellitePosition)
    }
}