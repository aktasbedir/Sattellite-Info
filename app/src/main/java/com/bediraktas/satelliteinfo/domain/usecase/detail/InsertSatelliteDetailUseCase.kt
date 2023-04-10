package com.bediraktas.satelliteinfo.domain.usecase.detail

import com.bediraktas.satelliteinfo.data.model.entity.SatelliteDetail
import com.bediraktas.satelliteinfo.domain.repository.SatelliteRepository
import com.bediraktas.satelliteinfo.domain.usecase.BaseUseCase
import com.bediraktas.satelliteinfo.util.Resource
import javax.inject.Inject

class InsertSatelliteDetailUseCase @Inject constructor(
    private val repository: SatelliteRepository
) : BaseUseCase<InsertSatelliteDetailUseCase.Request, Unit>() {
    override suspend fun execute(request: Request): Resource<Unit> {
        return try {
            request.satelliteDetail?.let { repository.insertSatelliteDetail(it) }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e.toString())
        }
    }

    data class Request(val satelliteDetail: SatelliteDetail?)

}