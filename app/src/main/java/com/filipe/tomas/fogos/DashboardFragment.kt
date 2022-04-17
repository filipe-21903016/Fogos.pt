package com.filipe.tomas.fogos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.filipe.tomas.fogos.databinding.FragmentDashboardBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

class DashboardFragment : Fragment() {
    private lateinit var binding : FragmentDashboardBinding
    private var param1: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set screen name
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.dashboard)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        binding = FragmentDashboardBinding.bind(view)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.btAddFire.setOnClickListener{
            NavigationManager.goToFireRegistrationFragment(parentFragmentManager)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}