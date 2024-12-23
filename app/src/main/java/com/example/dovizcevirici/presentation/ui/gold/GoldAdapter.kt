package com.example.dovizcevirici.presentation.ui.gold

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dovizcevirici.data.model.Result
import com.example.dovizcevirici.databinding.ItemListDashboardBinding

class GoldAdapter(private val goldList: List<Result> = emptyList()): RecyclerView.Adapter<GoldAdapter.GoldViewHolder>(){

    class GoldViewHolder(private val binding: ItemListDashboardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gold: Result) {
            binding.goldName.text = gold.name
            binding.goldBuying.text = gold.buy
            binding.goldSelling.text = gold.sell
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoldViewHolder {
        val binding =
            ItemListDashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoldViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoldViewHolder, position: Int) {
        val gold = goldList[position]
        holder.bind(gold)
    }

    override fun getItemCount(): Int {
       return goldList.size
    }
}