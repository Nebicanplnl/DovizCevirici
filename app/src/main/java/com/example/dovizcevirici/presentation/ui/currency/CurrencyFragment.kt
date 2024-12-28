package com.example.dovizcevirici.presentation.ui.currency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dovizcevirici.R
import com.example.dovizcevirici.common.ext.viewBinding
import com.example.dovizcevirici.databinding.FragmentCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrencyFragment : Fragment() {

    private lateinit var currencyAdapter2: CurrencyAdapter
    private val currencyViewModel: CurrencyViewModel by viewModels()
    private val binding by viewBinding(FragmentCurrencyBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // RecyclerView Ayarları
        currencyAdapter2 = CurrencyAdapter(emptyList())
        binding.recyclerView2.adapter = currencyAdapter2


        launchRepeatWithViewLifecycle {
            currencyViewModel.uiState.collect {
                if (it.gold.result.isNotEmpty()) {
                    currencyAdapter2 = CurrencyAdapter(it.gold.result)
                    binding.recyclerView2.adapter = currencyAdapter2
                }
            }
        }
    }
    inline fun Fragment.launchRepeatWithViewLifecycle(
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        crossinline block: suspend CoroutineScope.() -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
                block()
            }
        }
    }

}