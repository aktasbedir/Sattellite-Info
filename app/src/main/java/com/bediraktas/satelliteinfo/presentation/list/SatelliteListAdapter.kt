package com.bediraktas.satelliteinfo.presentation.list

import android.graphics.Color
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bediraktas.satelliteinfo.databinding.ItemSatelliteListBinding
import com.bediraktas.satelliteinfo.domain.model.SatelliteUIModel
import com.bediraktas.satelliteinfo.common.viewBinding


class SatelliteListAdapter(
    private val onClick: (SatelliteUIModel) -> Unit,
) : RecyclerView.Adapter<SatelliteListAdapter.SatelliteListViewHolder>() {

    private val dataList: ArrayList<SatelliteUIModel> = arrayListOf()

    inner class SatelliteListViewHolder(private val binding: ItemSatelliteListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SatelliteUIModel) {
            with(binding) {
                item.activeImg?.let { statusImg.setImageResource(it) }

                statusTv.apply {
                    text = item.activeText
                    if (item.active == false) {
                        nameTv.setTextColor(Color.GRAY)
                        statusTv.setTextColor(Color.GRAY)
                    }
                }

                nameTv.text = item.name

                root.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SatelliteListViewHolder {
        return SatelliteListViewHolder(parent.viewBinding(ItemSatelliteListBinding::inflate))
    }

    override fun onBindViewHolder(holder: SatelliteListViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateItemList(itemList: MutableList<SatelliteUIModel?>?) {
        this.dataList.clear()
        itemList?.let {
            this.dataList.addAll(itemList.filterNotNull())
        }
        notifyDataSetChanged()
    }

}