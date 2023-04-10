package com.bediraktas.satelliteinfo.data.model.response

import com.bediraktas.satelliteinfo.data.model.entity.SatellitePosition
import com.google.gson.annotations.SerializedName

data class SatellitePositionsList(
    @SerializedName("list")
    val list: List<SatellitePosition>
)