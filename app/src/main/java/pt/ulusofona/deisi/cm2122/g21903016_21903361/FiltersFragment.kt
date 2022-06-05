package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFiltersBinding
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
        var seekbarRadius = 0

        super.onStart()
        ArrayAdapter.createFromResource(
            activity as Context,
            R.array.distritos,
            R.layout.dropdown_item
        ).also {
            binding.acDistrict.setAdapter(it)
            binding.acDistrict.setText(Filter.district)
        }


        setupSeekbar {
            seekbarRadius = it
        }

        binding.btClearFilters.setOnClickListener {
            binding.seekbar.progress = 0
            binding.acDistrict.clearComposingText()
            Filter.reset()
            NavigationManager.popFragment(parentFragmentManager)
        }

        binding.btSubmit.setOnClickListener {
            Filter.district = binding.acDistrict.text.toString()
            Filter.radius = seekbarRadius
            NavigationManager.popFragment(parentFragmentManager)
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

    private fun setupSeekbar(onStop: (Int) -> Unit) {
        val step = 10
        val seek = binding.seekbar
        seek.max = 300 / step
        seek.progress = if (Filter.radiusFilterIsSet()) Filter.radius / step else 0
        var cProgress = 0

        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                cProgress = progress * step
                binding.progressValue.text = "${cProgress}km"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                onStop(cProgress)
            }
        })
    }
}