package com.filipe.tomas.fogos

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.filipe.tomas.fogos.R
import com.filipe.tomas.fogos.databinding.FragmentFireDetailsBinding
import com.filipe.tomas.fogos.FireUi

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

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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