package com.bediraktas.satelliteinfo.data.model.response

import com.google.gson.annotations.SerializedName

data class PositionCoordinate(
    @SerializedName("posX")
    val posX: Float,
    @SerializedName("posY")
    val posY: Float
)