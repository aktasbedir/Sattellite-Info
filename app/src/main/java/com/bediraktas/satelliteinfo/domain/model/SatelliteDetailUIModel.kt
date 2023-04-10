package com.bediraktas.satelliteinfo.domain.model

import com.bediraktas.satelliteinfo.data.model.entity.SatellitePosition

data class SatelliteDetailUIModel(
    val satelliteId: Int?,
    val heightMassText: String?,
    val costText: String?,
    val dateText: String?,
    val lastPosition: List<SatellitePosition?>?
)
