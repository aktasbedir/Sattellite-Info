package com.bediraktas.satelliteinfo.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bediraktas.satelliteinfo.data.model.response.PositionCoordinate


@Entity(tableName = "satellite_position")
data class SatellitePosition(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val positions: List<PositionCoordinate>
)
