package com.example.dovizcevirici.presentation.ui.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dovizcevirici.common.formatAsTwoDecimal
import com.example.dovizcevirici.data.model.Result
import com.example.dovizcevirici.databinding.ItemListDashboardBinding

class CurrencyAdapter(private val goldList: List<Result> = emptyList()): RecyclerView.Adapter<CurrencyAdapter.GoldViewHolder>(){

    class GoldViewHolder(private val binding: ItemListDashboardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gold: Result) {
            binding.goldName.text = gold.name
            binding.goldBuying.text = gold.buy.formatAsTwoDecimal()
            binding.goldSelling.text = gold.sell.formatAsTwoDecimal()
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