package com.bediraktas.satelliteinfo.presentation.list

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bediraktas.satelliteinfo.R
import com.bediraktas.satelliteinfo.databinding.FragmentSatelliteListBinding
import com.bediraktas.satelliteinfo.domain.model.SatelliteUIModel
import com.bediraktas.satelliteinfo.presentation.BaseFragment
import com.bediraktas.satelliteinfo.common.DividerItemDecorator
import com.bediraktas.satelliteinfo.common.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteListFragment :
    BaseFragment<SatelliteListViewModel>(R.layout.fragment_satellite_list) {

    override val binding by viewBinding(FragmentSatelliteListBinding::bind)
    override val viewModel: SatelliteListViewModel by viewModels()
    private val listAdapter by lazy { SatelliteListAdapter(::onClicked) }


    override fun observeViewModel(viewModel: SatelliteListViewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listFlow.collect {
                    it?.let {
                        when (it) {
                            SatelliteListViewState.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            is SatelliteListViewState.ListLoaded -> {
                                listAdapter.updateItemList(it.list?.toMutableList())
                                binding.progressBar.visibility = View.GONE
                            }
                            is SatelliteListViewState.Failure -> {
                                Toast.makeText(context, it.errorName, Toast.LENGTH_SHORT)
                                    .show()
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }

    override fun initUI() {
        viewModel.getSatelliteList()
        with(binding) {

            rv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                ContextCompat.getDrawable(context, R.drawable.divider_gray_1dp)?.let {
                    DividerItemDecorator(it)
                }?.let {
                    addItemDecoration(it)
                }
                adapter = listAdapter
            }

            searchView.apply {
                clearFocus()
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        viewModel.makeSearch(newText.orEmpty())
                        return true
                    }
                })
            }
        }
    }

    private fun onClicked(model: SatelliteUIModel) {
        SatelliteListFragmentDirections.actionListFragmentToDetailsFragment(
            id = model.satelliteId ?: 0,
            name = model.name.orEmpty()
        ).also(findNavController()::navigate)
    }
}