package com.bediraktas.satelliteinfo.presentation.list

import com.bediraktas.satelliteinfo.domain.model.SatelliteUIModel

sealed class SatelliteListViewState {
    object Loading : SatelliteListViewState()
    class ListLoaded(val list: List<SatelliteUIModel?>?) : SatelliteListViewState()
    class Failure(val errorName: String) : SatelliteListViewState()
}