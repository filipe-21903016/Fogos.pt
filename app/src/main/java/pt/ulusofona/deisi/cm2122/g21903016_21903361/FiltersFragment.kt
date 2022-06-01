package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFiltersBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFireRegistrationBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FiltersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FiltersFragment : Fragment() {
    private lateinit var binding: FragmentFiltersBinding
    private lateinit var viewModel: FireViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.filters)
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_filters, container, false)
        binding = FragmentFiltersBinding.bind(view)
        // Inflate the layout for this fragment
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

    }


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onResume() {
        super.onResume()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onPause() {
        super.onPause()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
    }
}