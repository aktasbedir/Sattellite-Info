package com.bediraktas.satelliteinfo.presentation.detail

import com.bediraktas.satelliteinfo.data.model.response.PositionCoordinate
import com.bediraktas.satelliteinfo.domain.model.SatelliteDetailUIModel


sealed class SatelliteDetailViewState {
    object Loading : SatelliteDetailViewState()
    class DataLoaded(val satelliteDetailUIModel: SatelliteDetailUIModel?) : SatelliteDetailViewState()
    class PositionChange(val position: PositionCoordinate?) : SatelliteDetailViewState()
    class Failure(val error: String?) : SatelliteDetailViewState()
}
