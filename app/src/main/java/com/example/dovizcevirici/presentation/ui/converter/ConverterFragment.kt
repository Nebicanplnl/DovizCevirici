package com.example.dovizcevirici.presentation.ui.converter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dovizcevirici.R
import com.example.dovizcevirici.common.ext.viewBinding
import com.example.dovizcevirici.databinding.FragmentConverterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConverterFragment : Fragment() {

    private val binding by viewBinding(FragmentConverterBinding::bind)
    private val viewModel: ConverterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getConverter()

        launchRepeatWithViewLifecycle {
            viewModel.uiState.collect {
                if (it.spinnerList.isNotEmpty()) {

                    val adapter: ArrayAdapter<String> = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        it.spinnerList
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerFrom.adapter = adapter // Adapter'i spinner'a bağlayın
                    binding.spinnerTo.adapter = adapter // Adapter'i spinner'a bağlayın
                }
            }
        }

        binding.editTextFrom.addTextChangedListener { it ->
            binding.textTo.text = it.toString()
        }

        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Seçilen öğe ile ilgili işlemler burada yapılabilir
                val selectedItem = parent.getItemAtPosition(position).toString()

                // Örneğin, seçilen öğeyi loglayabiliriz
                Log.d("Spinner", "Seçilen öğe: $selectedItem")

                viewModel.base = selectedItem

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Hiçbir şey seçilmediğinde yapılacak işlemler
                Log.d("Spinner", "Hiçbir öğe seçilmedi")
            }
        }

        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Seçilen öğe ile ilgili işlemler burada yapılabilir
                val selectedItem = parent.getItemAtPosition(position).toString()

                // Örneğin, seçilen öğeyi loglayabiliriz
                Log.d("Spinner", "Seçilen öğe: $selectedItem")

                viewModel.to = selectedItem
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Hiçbir şey seçilmediğinde yapılacak işlemler
                Log.d("Spinner", "Hiçbir öğe seçilmedi")
            }
        }

        binding.clearButton.setOnClickListener {
            binding.editTextFrom.text.clear()
            binding.textTo.text = ""
        }

        // Çevir butonuna tıklanırsa EditText'teki veriyi TextView'e yazdır
        binding.converterButton.setOnClickListener {
            val amount = binding.editTextFrom.text.toString()
            if (amount.isNotEmpty()) {
                binding.textTo.text = "$amount"
            } else {
                binding.textTo.text = "Lütfen bir miktar girin"
            }
            viewModel.getExchange()

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
