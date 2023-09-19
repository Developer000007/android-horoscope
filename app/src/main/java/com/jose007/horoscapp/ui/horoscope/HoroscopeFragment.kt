package com.jose007.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jose007.horoscapp.databinding.FragmentHoroscopeBinding
import com.jose007.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private var _binding: FragmentHoroscopeBinding? = null//VAlor variable
    private val binding get() = _binding!! //Valor fijo
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    private lateinit var horoscopeadapter: HoroscopeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InitUI()
    }

    private fun InitUI() {
        InitList()
        InitState()
    }

    private fun InitList() {
        horoscopeadapter = HoroscopeAdapter()
        binding.rvHoroscope.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }
    }

    private fun InitState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}