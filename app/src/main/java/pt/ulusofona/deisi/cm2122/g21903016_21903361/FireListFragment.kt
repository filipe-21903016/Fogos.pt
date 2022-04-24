package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ulusofona.deisi.cm2122.g21903016_21903361.adapters.FireListAdapter
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFireListBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel

private const val ARG_FIRES = "param1"

class FireListFragment : Fragment() {
    private lateinit var binding: FragmentFireListBinding
    private lateinit var viewModel : FireViewModel

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
}