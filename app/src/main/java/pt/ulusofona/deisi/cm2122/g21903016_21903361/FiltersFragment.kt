package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFiltersBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.Filter
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel

class FiltersFragment() : Fragment() {
    private val TAG = FiltersFragment::class.java.simpleName
    private lateinit var binding: FragmentFiltersBinding
    private lateinit var viewModel: FireViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.filters)
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_filters, container, false)
        binding = FragmentFiltersBinding.bind(view)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onStart() {
        val seekbar_radius = 0

        super.onStart()
        ArrayAdapter.createFromResource(
            activity as Context,
            R.array.distritos_com_none,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerDistrito.adapter = arrayAdapter
        }
        setupSeekbar()




        binding.btSubmit.setOnClickListener {


            Filter.district = if (binding.spinnerDistrito.selectedItem.toString() == "----") ""
            else binding.spinnerDistrito.selectedItem.toString()
            NavigationManager.goToFireListFragment(parentFragmentManager)
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

    private fun setupSeekbar() {
        val step = 10
        val seek = binding.seekbar
        seek.max = 300 / step
        seek.progress = 150 / step

        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}