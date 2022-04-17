package com.filipe.tomas.fogos

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import com.filipe.tomas.fogos.R
import com.filipe.tomas.fogos.databinding.FragmentFireRegistrationBinding
import com.filipe.tomas.fogos.viewmodels.FireViewModel

class FireRegistrationFragment : Fragment() {
    private lateinit var binding: FragmentFireRegistrationBinding
    private lateinit var viewModel: FireViewModel

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
        ArrayAdapter.createFromResource(
            activity as Context,
            R.array.distritos,
            android.R.layout.simple_spinner_item
        ).also {
                arrayAdapter -> arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerDistrito.adapter = arrayAdapter
        }

        binding.btSubmit.setOnClickListener {
            val name: String = binding.etNome.text.toString()
            val cc: String = binding.etCc.text.toString()
            val district: String = binding.spinnerDistrito.selectedItem.toString()
            binding.etNome.hideKeyboard()
            binding.etCc.hideKeyboard()
            if (validateEntries(name, cc))
            {
                viewModel.onNewRegistration(name, cc, district, null)
                NavigationManager.goToFireListFragment(parentFragmentManager)
            }

        }
    }
    private fun validateEntries(name: String, cc: String): Boolean {
        //todo implement
        //val nameRegex = "([a-z])".toRegex()
        //return nameRegex.matches(name)
          //      && cc.isDigitsOnly()
        return true
    }

    fun View.hideKeyboard() {
        val imm =
            (activity as Context).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}