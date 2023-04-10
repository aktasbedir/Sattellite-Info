package com.bediraktas.satelliteinfo.domain.usecase.detail

import com.bediraktas.satelliteinfo.data.model.entity.SatellitePosition
import com.bediraktas.satelliteinfo.domain.repository.SatelliteRepository
import com.bediraktas.satelliteinfo.domain.usecase.BaseUseCase
import com.bediraktas.satelliteinfo.common.Resource
import javax.inject.Inject

class InsertSatellitePositionUseCase @Inject constructor(
    private val repository: SatelliteRepository
) : BaseUseCase<InsertSatellitePositionUseCase.Request, Unit>() {
    override suspend fun execute(request: Request): Resource<Unit> {
        return try {
            request.satellitePosition?.let { repository.insertSatellitePosition(it) }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e.toString())
        }
    }

    data class Request(val satellitePosition: SatellitePosition?)
}