package com.bediraktas.satelliteinfo.domain.usecase.detail

import com.bediraktas.satelliteinfo.data.model.entity.SatellitePosition
import com.bediraktas.satelliteinfo.domain.repository.SatelliteRepository
import com.bediraktas.satelliteinfo.domain.usecase.BaseUseCase
import com.bediraktas.satelliteinfo.common.Constant.SATELLITE_POSITION_FILE
import com.bediraktas.satelliteinfo.common.Resource


import javax.inject.Inject

class GetPositionUseCase @Inject constructor(
    private val repository: SatelliteRepository,
    private val insertSatellitePositionUseCase: InsertSatellitePositionUseCase
) : BaseUseCase<GetPositionUseCase.Request, List<SatellitePosition?>?>() {
    override suspend fun execute(request: Request): Resource<List<SatellitePosition?>?> {
        return try {
            when (val positions =
                repository.getSatellitePositions(SATELLITE_POSITION_FILE, request.satelliteId)) {
                is Resource.Success -> {
                    insertSatellitePositionUseCase.execute(
                        InsertSatellitePositionUseCase.Request(
                            positions.data?.find { it?.id == request.satelliteId })
                    )
                    Resource.Success(positions.data)
                }
                is Resource.Failure -> Resource.Failure(error = positions.error)
            }
        } catch (e: Exception) {
            Resource.Failure(e.toString())
        }
    }

    data class Request(val satelliteId: Int)
}