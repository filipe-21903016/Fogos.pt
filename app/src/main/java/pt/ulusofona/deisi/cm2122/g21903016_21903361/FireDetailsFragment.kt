package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFireDetailsBinding


private const val ARG_FIRE = "param1"

class FireDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFireDetailsBinding
    private var fireUi: FireUi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fireUi = it.getParcelable(ARG_FIRE)
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

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        binding.nome.text = "${getString(R.string.name)}: ${fireUi?.name}"
        binding.cc.text = "${getString(R.string.id_number)}: ${fireUi?.cc}"
        binding.status.text = "${getString(R.string.status)}: ${fireUi?.status}"
        binding.concelho.text = "${getString(R.string.county)}: ${fireUi?.concelho}"
        binding.observacoes.text = "${getString(R.string.observations)}: ${fireUi?.observacoes}"
        binding.aereos.text = "${getString(R.string.aerial)}: ${fireUi?.aereos.toString()}"
        binding.datetime.text = "${getString(R.string.date)}: ${fireUi?.getDateTime()}"
        binding.freguesia.text = "${getString(R.string.town)}: ${fireUi?.freguesia}"
        binding.operacionais.text = "${getString(R.string.operational)}: ${fireUi?.operacionais.toString()}"
        binding.veiculos.text = "${getString(R.string.vehicle)}: ${fireUi?.veiculos.toString()}"
        binding.distrito.text = "${getString(R.string.district)}: ${fireUi?.district}"
        binding.ivFirePicture.setImageBitmap(fireUi?.pictureBitmap)
        if (fireUi?.pictureBitmap != null)
            binding.tvOtherPictureLabel.text = getString(R.string.picture)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.fire_details)
        val view = inflater.inflate(R.layout.fragment_fire_details, container, false)
        binding = FragmentFireDetailsBinding.bind(view)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(fireUi: FireUi) =
            FireDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_FIRE, fireUi)
                }
            }
    }
}