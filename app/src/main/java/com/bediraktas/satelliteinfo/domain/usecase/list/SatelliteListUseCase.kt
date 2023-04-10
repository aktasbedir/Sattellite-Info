package com.bediraktas.satelliteinfo.domain.usecase.list

import com.bediraktas.satelliteinfo.domain.mapper.SatelliteUIModelMapper
import com.bediraktas.satelliteinfo.domain.model.SatelliteUIModel
import com.bediraktas.satelliteinfo.domain.repository.SatelliteRepository
import com.bediraktas.satelliteinfo.domain.usecase.BaseUseCase
import com.bediraktas.satelliteinfo.util.Resource
import kotlinx.coroutines.delay
import javax.inject.Inject

class SatelliteListUseCase @Inject constructor(
    private val repository: SatelliteRepository
) : BaseUseCase<SatelliteListUseCase.Request, List<SatelliteUIModel?>?>() {

    override suspend fun execute(request: Request): Resource<List<SatelliteUIModel?>?> {
        return try {
            delay(2000)
            when (val satellites = repository.getSatellites(request.fileName)) {
                is Resource.Success -> Resource.Success(
                    SatelliteUIModelMapper.satelliteFileResponseToUIModel(
                        satellites.data
                    )
                )
                is Resource.Failure -> Resource.Failure(satellites.error)
            }
        } catch (e: Exception) {
            Resource.Failure(e.toString())
        }
    }

    data class Request(val fileName: String)
}
