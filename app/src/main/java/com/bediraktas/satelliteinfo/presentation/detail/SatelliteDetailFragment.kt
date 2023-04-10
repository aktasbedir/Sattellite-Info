package com.bediraktas.satelliteinfo.presentation.detail

import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bediraktas.satelliteinfo.R
import com.bediraktas.satelliteinfo.databinding.FragmentSatelliteDetailBinding
import com.bediraktas.satelliteinfo.domain.model.SatelliteDetailUIModel
import com.bediraktas.satelliteinfo.presentation.BaseFragment
import com.bediraktas.satelliteinfo.util.partialBold
import com.bediraktas.satelliteinfo.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailFragment :
    BaseFragment<SatelliteDetailViewModel>(R.layout.fragment_satellite_detail) {

    override val binding by viewBinding(FragmentSatelliteDetailBinding::bind)
    override val viewModel: SatelliteDetailViewModel by viewModels()
    private val args: SatelliteDetailFragmentArgs by navArgs()

    override fun observeViewModel(viewModel: SatelliteDetailViewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.satelliteDetailFlow.collect {
                    it?.let {
                        when (it) {
                            SatelliteDetailViewState.Loading -> {
                                binding.progressBar.visibility = VISIBLE
                            }
                            is SatelliteDetailViewState.DataLoaded -> {
                                updateUI(it.satelliteDetailUIModel)
                                viewModel.changePosition(it.satelliteDetailUIModel?.lastPosition)
                                binding.progressBar.visibility = GONE
                            }
                            is SatelliteDetailViewState.PositionChange -> {
                                binding.lastPositionTv.partialBold(
                                    boldString = resources.getString(R.string.last_position),
                                    regularText = "(".plus(
                                        it.position?.posX.toString()
                                            .plus(it.position?.posY.toString())
                                    ).plus(")")
                                )
                                binding.progressBar.visibility = GONE
                            }
                            is SatelliteDetailViewState.Failure -> {
                                Toast.makeText(
                                    context,
                                    it.error,
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.i("TAG", "observeViewModel:${it.error} ")
                                binding.progressBar.visibility = GONE
                            }
                        }
                    }
                }
            }
        }
    }

    override fun initUI() {
        viewModel.getSatelliteDetail(args.id)
    }

    private fun updateUI(data: SatelliteDetailUIModel?) {
        with(binding) {
            satelliteNameTv.text = args.name
            dateTv.text = data?.dateText
            heightMassTv.partialBold(
                resources.getString(R.string.height_mass),
                data?.heightMassText
            )
            costTv.partialBold(
                boldString = resources.getString(R.string.cost),
                data?.costText
            )
            lastPositionTv.partialBold(
                boldString = resources.getString(R.string.last_position),
                regularText = ("(").plus(
                    data?.lastPosition?.get(0)?.positions?.get(0)?.posX.toString()
                        .plus(data?.lastPosition?.get(0)?.positions?.get(0)?.posY.toString())
                        .plus(")")
                )
            )
        }
    }
}