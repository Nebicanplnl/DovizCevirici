package com.example.dovizcevirici.presentation.ui.converter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.dovizcevirici.R
import com.example.dovizcevirici.common.ext.viewBinding
import com.example.dovizcevirici.databinding.FragmentConverterBinding
import com.example.dovizcevirici.presentation.ui.converter.ConvertList.exchangeRates
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

                    val currencyList = listOf(
                        "USD", "EUR", "GBP", "JPY", "CHF", "CAD", "AUD",
                        "SEK", "DKK", "NOK", "SAR", "BRL", "MXN", "CZK",
                        "PLN", "HUF", "RUB", "ZAR", "KWD", "CNY", "CNH",
                        "INR", "AED", "KRW", "HKD", "SGD", "MYR", "MXV",
                        "IDR", "THB", "VND", "PHP", "NZD", "BHD", "TWD",
                    )
                    val adapter: ArrayAdapter<String> = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        currencyList
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    val toCurrencyList = listOf("TRY")
                    val toAdapter: ArrayAdapter<String> = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        toCurrencyList
                    )
                    toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    binding.spinnerFrom.adapter = adapter // Adapter'i spinner'a bağlayın
                    binding.spinnerTo.adapter = toAdapter // Adapter'i spinner'a bağlayın
                }
            }
        }

        binding.spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Seçilen öğe ile ilgili işlemler burada yapılabilir
                val selectedItem = parent.getItemAtPosition(position).toString()

                Log.d("Spinner", "Seçilen öğe: $selectedItem")

                viewModel.base = selectedItem

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("Spinner", "Hiçbir öğe seçilmedi")
            }
        }

        binding.spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
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

        binding.converterButton.setOnClickListener {
            val fromCurrency = binding.spinnerFrom.selectedItem.toString()
            val amountString = binding.editTextFrom.text.toString()

            if (amountString.isNotEmpty()) {
                val amount = amountString.toDoubleOrNull() // Miktarı Double'a çevir

                if (amount != null) {
                    convertCurrency(fromCurrency, amount)
                } else {
                    Toast.makeText(requireContext(), "Geçerli bir miktar giriniz.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Lütfen bir miktar giriniz.", Toast.LENGTH_SHORT).show()
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

        private fun convertCurrency(fromCurrency: String, amount: Double) {
            val rate = exchangeRates[fromCurrency]

            if (rate != null) {
                val result = amount * rate
                binding.textTo.text = String.format("%.2f TRY", result)
            } else {
                binding.textTo.text = "Hata: Döviz kuru bulunamadı."
            }
        }
    }

