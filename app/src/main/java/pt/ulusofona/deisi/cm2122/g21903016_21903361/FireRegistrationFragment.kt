package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFireRegistrationBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel
import java.text.SimpleDateFormat
import java.util.*

class FireRegistrationFragment : Fragment() {
    private val TAG = MainActivity::class.java.simpleName
    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var binding: FragmentFireRegistrationBinding
    private lateinit var viewModel: FireViewModel
    private var imageBitmap: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.fire_registry)
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


        binding.btTakePicture.setOnClickListener{
            dispatchTakePictureIntent()
        }

        binding.btSubmit.setOnClickListener {
            val name: String = binding.etNome.text.toString()
            val cc: String = binding.etCc.text.toString()
            val district: String = binding.spinnerDistrito.selectedItem.toString()
            binding.etNome.hideKeyboard()
            binding.etCc.hideKeyboard()
            if (isAlpha(binding.etNome.text.toString()) && !binding.etCc.text.isNullOrEmpty())
            {
                viewModel.onNewRegistration(name, cc, district, imageBitmap)
                NavigationManager.goToFireListFragment(parentFragmentManager)
            }

        }
    }

    fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }catch (e : ActivityNotFoundException){
            Log.wtf(TAG, "Activity not found")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            imageBitmap = data?.extras?.get("data") as Bitmap
            binding.ivPicture.setImageBitmap(imageBitmap)
        }
    }

    fun View.hideKeyboard() {
        val imm =
            (activity as Context).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
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

    private fun isAlpha(str: String): Boolean{
        for (c in str){
            if (!c.isLetter() && c != ' ')
                return false
        }
        return true
    }
}