package com.bediraktas.satelliteinfo.domain.mapper

import com.bediraktas.satelliteinfo.data.model.entity.SatelliteDetail
import com.bediraktas.satelliteinfo.data.model.entity.SatellitePosition
import com.bediraktas.satelliteinfo.domain.model.SatelliteDetailUIModel
import java.text.NumberFormat

object SatelliteDetailUIModelMapper {

    fun satelliteDetailToUIModel(
        list: List<SatelliteDetail?>?,
        listPosition: List<SatellitePosition?>?
    ): List<SatelliteDetailUIModel?>? {
        return list?.map { satelliteDetail ->
            SatelliteDetailUIModel(
                satelliteId = satelliteDetail?.id,
                dateText = satelliteDetail?.firstFlight,
                heightMassText = satelliteDetail?.height.toString().plus("/")
                    .plus(
                        NumberFormat.getNumberInstance().format(satelliteDetail?.mass).toString()
                    ),
                costText = (NumberFormat.getNumberInstance()
                    .format(satelliteDetail?.costPerLaunch)).toString(),
                lastPosition = listPosition?.filter { position -> satelliteDetail?.id == position?.id }
            )
        }
    }
}