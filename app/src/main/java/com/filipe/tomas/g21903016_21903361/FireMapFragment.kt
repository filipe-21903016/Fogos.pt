package com.filipe.tomas.g21903016_21903361

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.filipe.tomas.g21903016_21903361.R
import com.filipe.tomas.g21903016_21903361.databinding.FragmentFireMapBinding


class FireMapFragment : Fragment() {
    private lateinit var binding: FragmentFireMapBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.fire_map)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fire_map, container, false)
        binding = FragmentFireMapBinding.bind(view)
        return binding.root
    }
}