package com.filipe.tomas.g21903016_21903361

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.filipe.tomas.g21903016_21903361.adapters.FireListAdapter
import com.filipe.tomas.g21903016_21903361.databinding.FragmentFireListBinding
import com.filipe.tomas.g21903016_21903361.viewmodels.FireViewModel

private const val ARG_FIRES = "param1"

class FireListFragment : Fragment() {
    private lateinit var binding: FragmentFireListBinding
    private lateinit var viewModel : FireViewModel
    private var fires: ArrayList<FireUi>? = null

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
        //set screen name
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.fire_list)
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_fire_list, container, false)
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        binding = FragmentFireListBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.rvFires.layoutManager = LinearLayoutManager(activity as Context)
        binding.rvFires.adapter = FireListAdapter(parentFragmentManager, viewModel.getAllFires())
    }

    companion object {
        @JvmStatic
        fun newInstance(fires: ArrayList<FireUi>) =
            FireListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_FIRES, fires)
                }
            }
    }
}