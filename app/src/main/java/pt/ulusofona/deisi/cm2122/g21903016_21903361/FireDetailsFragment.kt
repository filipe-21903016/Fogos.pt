package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFireDetailsBinding


private const val ARG_FIRE = "param1"

class FireDetailsFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentFireDetailsBinding
    private var fireUi: FireUi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fireUi = it.getParcelable(ARG_FIRE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.fire_details)
        val view = inflater.inflate(R.layout.fragment_fire_details, container, false)
        binding = FragmentFireDetailsBinding.bind(view)
        return binding.root
    }

    private fun showDetails(onSucess: () -> Unit) {
        if (fireUi == null) {
            binding.llFireDetails.visibility = View.GONE
            binding.tvErrorMessage.visibility = View.VISIBLE
        } else {
            binding.tvErrorMessage.visibility = View.GONE
            binding.llFireDetails.visibility = View.VISIBLE
            onSucess()
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
        showDetails {

            //Districto
            binding.tvLocalizacaoDistrict.text =
                "${getString(R.string.district)}: ${fireUi?.district ?: fireUi?.missingInfoString()}"

            //Freguesia
            binding.tvLocalizacaoFreguesia.text =
                "${getString(R.string.town)}: ${fireUi?.freguesia ?: fireUi?.missingInfoString()}"

            //Concelho
            binding.tvLocalizacaoConcelho.text =
                "${getString(R.string.county)}: ${fireUi?.concelho ?: fireUi?.missingInfoString()}"

            //Status
            binding.tvEstado.text = fireUi?.status ?: fireUi?.missingInfoString()

            //Status Color
            binding.tvEstado.compoundDrawablesRelative.filterNotNull().forEach {
                it.setTint(Color.parseColor("#ff${fireUi?.statusColor}"))
            }

            //Date/Time
            binding.tvDatetime.text = fireUi?.getDateTime()

            //Observations
            binding.tvObservations.text = fireUi?.observacoes ?: fireUi?.missingInfoString()

            //Resources Aerial
            binding.tvResourcesAerial.text =
                "${getString(R.string.aerial)}: ${fireUi?.aereos.toString()}"

            //Resources Terrain
            binding.tvResourcesTerrain.text =
                "${getString(R.string.vehicle)}: ${fireUi?.veiculos.toString()}"

            //Resources Man
            binding.tvResourcesMan.text =
                "${getString(R.string.operational)}: ${fireUi?.operacionais.toString()}"

            //Image
            if (fireUi?.picture != null) {
                binding.ivFirePicture.setImageBitmap(
                    BitmapFactory.decodeByteArray(
                        fireUi?.picture,
                        0,
                        fireUi?.picture!!.size
                    )
                )
            } else {
                binding.tvPictureLabel.visibility = View.GONE
                binding.ivFirePicture.visibility = View.GONE
            }


            //Author Name
            binding.tvAutorNome.text =
                "${getString(R.string.name)}: ${fireUi?.name ?: fireUi?.missingInfoString()}"
            //Author Id
            binding.tvAutorCc.text =
                "${getString(R.string.id_number)}: ${fireUi?.cc ?: fireUi?.missingInfoString()}"


            //Last Updated
            binding.tvLastUpdated.text =
                "${getString(R.string.last_updated)} ${fireUi?.lastUpdatedTime()}"

        }
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

    override fun onMapReady(map: GoogleMap) {

    }
}