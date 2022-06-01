package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.content.Context
import android.os.Bundle
import android.service.autofill.FillResponse
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2122.g21903016_21903361.adapters.FireListAdapter
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFireListBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel

class FireListFragment : Fragment() {
    private lateinit var binding: FragmentFireListBinding
    private lateinit var viewModel : FireViewModel

    private var adapter = FireListAdapter(::onFireClick)



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
        setHasOptionsMenu(true)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onStart() {
        super.onStart()
        binding.rvFires.layoutManager = LinearLayoutManager(activity as Context)
        binding.rvFires.adapter = adapter
        viewModel.onGetFires { updateFires(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_filter -> {
            NavigationManager.goToFilterFragment(parentFragmentManager)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun onFireClick(fireUi: FireUi){
        NavigationManager.goToFireDetails(parentFragmentManager, fireUi)
    }


    private fun updateFires(fireList: List<FireUi>) {
        val fires = fireList
        CoroutineScope(Dispatchers.Main).launch {
            showFires(fireList.isNotEmpty())
            adapter.updateItems(fires)
        }
    }

    private fun showFires(show: Boolean)
    {
        if (show)
        {
            binding.tvErrorMessage.visibility = View.GONE
            binding.rvFires.visibility = View.VISIBLE
        } else {
            binding.rvFires.visibility = View.GONE
            binding.tvErrorMessage.visibility = View.VISIBLE
        }
    }
}