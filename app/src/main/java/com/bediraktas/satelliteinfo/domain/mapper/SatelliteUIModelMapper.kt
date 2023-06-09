package com.bediraktas.satelliteinfo.domain.mapper

import com.bediraktas.satelliteinfo.R
import com.bediraktas.satelliteinfo.data.model.response.Satellite
import com.bediraktas.satelliteinfo.domain.model.SatelliteUIModel


object SatelliteUIModelMapper {

    fun satelliteFileResponseToUIModel(list: List<Satellite?>?): List<SatelliteUIModel?>? {
        return list?.map {
            SatelliteUIModel(
                satelliteId = it?.id,
                name = it?.name,
                activeText = if (it?.active == true) "Active" else "Passive",
                activeImg = if (it?.active == true) (R.drawable.ic_green_circle) else (R.drawable.ic_red_circle),
                active = it?.active
            )
        }
    }
}