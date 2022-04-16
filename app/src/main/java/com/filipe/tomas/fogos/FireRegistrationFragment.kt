package com.filipe.tomas.fogos

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.filipe.tomas.fogos.R
import com.filipe.tomas.fogos.databinding.FragmentFireRegistrationBinding
import com.filipe.tomas.fogos.viewmodels.FireViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FireRegistrationFragment : Fragment() {
    private lateinit var binding : FragmentFireRegistrationBinding
    private lateinit var viewModel : FireViewModel
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_fire_registration, container, false)
        binding = FragmentFireRegistrationBinding.bind(view)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        binding.btSubmit.setOnClickListener{
            //todo validate entries
            val name: String = binding.etNome.text.toString()
            val cc: String = binding.etCc.text.toString()
            val district : String = binding.etDistrito.text.toString()
            viewModel.onNewRegistration(name, cc, district)
            NavigationManager.goToFireListFragment(parentFragmentManager)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FireRegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}