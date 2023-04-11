package com.bediraktas.satelliteinfo.domain.usecase.list

import com.bediraktas.satelliteinfo.domain.model.SatelliteUIModel
import com.bediraktas.satelliteinfo.domain.usecase.BaseUseCase
import com.bediraktas.satelliteinfo.common.Resource
import javax.inject.Inject

class SatelliteSearchUseCase @Inject constructor(
    private val satelliteListUseCase: SatelliteListUseCase
) : BaseUseCase<SatelliteSearchUseCase.Request, List<SatelliteUIModel?>?>() {

    private var cachedResult: Resource<List<SatelliteUIModel?>?>? = null

    override suspend fun execute(request: Request): Resource<List<SatelliteUIModel?>?> {
        return try {
            if (cachedResult == null) {
                cachedResult = satelliteListUseCase.execute(SatelliteListUseCase.Request(request.fileName))
            }

            val searchKey = request.searchKey.lowercase()

            val filteredList: Resource<List<SatelliteUIModel?>?> = cachedResult?.let { cachedResult ->
                when (cachedResult) {
                    is Resource.Success -> {
                        if (searchKey.isBlank()) {
                            // If search key is empty or blank, return all items
                            Resource.Success(cachedResult.data)
                        } else {
                            // Filter the cached result using search key
                            cachedResult.data?.filter {
                                containsBoyerMoore(it?.name?.lowercase(), searchKey)
                            }?.let { filteredList ->
                                Resource.Success(filteredList)
                            } ?: Resource.Success(null)
                        }
                    }
                    is Resource.Failure -> {
                        Resource.Failure(cachedResult.error)
                    }
                    else -> Resource.Failure("Unexpected error occurred")
                }
            } ?: Resource.Success(null)

            filteredList
        } catch (e: Exception) {
            Resource.Failure(e.toString())
        }
    }

    data class Request(val fileName: String, val searchKey: String)

    companion object {
        private fun containsBoyerMoore(text: String?, pattern: String): Boolean {
            if (text == null) return false

            val n = text.length
            val m = pattern.length

            if (n < m) return false

            val badCharShift = IntArray(256) { m }
            for (i in 0 until m - 1) {
                badCharShift[pattern[i].toInt()] = m - i - 1
            }

            var i = m - 1
            while (i < n) {
                var j = m - 1
                while (text[i] == pattern[j]) {
                    if (j == 0) {
                        return true
                    }
                    i--
                    j--
                }
                i += maxOf(badCharShift[text[i].toInt()], m - j)
            }

            return false
        }
    }
}
