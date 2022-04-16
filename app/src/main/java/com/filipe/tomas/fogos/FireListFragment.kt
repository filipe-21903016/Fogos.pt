package com.filipe.tomas.fogos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filipe.tomas.fogos.databinding.FragmentFireListBinding
import com.filipe.tomas.fogos.models.Fire

private const val ARG_FIRES = "param1"

class FireListFragment : Fragment() {
    private lateinit var binding: FragmentFireListBinding
    private var fires: ArrayList<Fire>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fires = it.getParcelableArrayList(ARG_FIRES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_fire_list, container, false)
        binding = FragmentFireListBinding.bind(view)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(fires: ArrayList<Fire>) =
            FireListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_FIRES, fires)
                }
            }
    }
}