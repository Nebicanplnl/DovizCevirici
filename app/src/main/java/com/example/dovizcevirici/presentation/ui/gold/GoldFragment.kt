package com.example.dovizcevirici.presentation.ui.gold

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dovizcevirici.R
import com.example.dovizcevirici.common.viewBinding
import com.example.dovizcevirici.databinding.FragmentGoldBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GoldFragment : Fragment() {

    private lateinit var goldAdapter: GoldAdapter
    private val goldViewModel: GoldViewModel by viewModels()
    private val binding by viewBinding(FragmentGoldBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_gold, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // RecyclerView Ayarları
        goldAdapter = GoldAdapter(emptyList())
        binding.recyclerView.adapter = goldAdapter


        // ViewModel'den verileri gözlemle
//        goldViewModel.uiState.observe(viewLifecycleOwner) { state ->
//            if (state.gold.isNotEmpty()) {
//                goldAdapter = GoldAdapter(state.gold)
//                recyclerView.adapter = goldAdapter
//            }
//        }
        launchRepeatWithViewLifecycle {
            goldViewModel.uiState.collect {
                if (it.gold.result.isNotEmpty()) {
                    goldAdapter = GoldAdapter(it.gold.result)
                    binding.recyclerView.adapter = goldAdapter
                }
            }
        }
    }

    inline fun Fragment.launchRepeatWithViewLifecycle(
        minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
        crossinline block: suspend CoroutineScope.() -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
                block()
            }
        }
    }

}