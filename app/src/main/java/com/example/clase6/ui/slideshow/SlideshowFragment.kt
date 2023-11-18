package com.example.clase6.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.clase6.R
import com.example.clase6.databinding.FragmentSlideshowBinding
import java.util.concurrent.Executors

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    private lateinit var surfaceView: SurfaceView
// private lateinit var cameraExecutor: CameraExecutor

    // This property is only valid between onCreateView and
    // onDestroyView.

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        surfaceView = view.findViewById(R.id.surfaceView)

        val cameraExecutor = Executors.newSingleThreadExecutor()
         cameraStart()

    }

    private fun cameraStart(){

    val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext()) // Procesamiento de imagen de la camara

    surfaceView.holder.addCallback( object: SurfaceHolder.Callback {


        override fun surfaceCreated(holder: SurfaceHolder) {

            val CameraProviderx = cameraProviderFuture.get() // tomamos las imagenes de la camara

            val preview = Preview.Builder().build()// mostrar las imagenes de la camara*/

            // elegimos una de las 2 camaras del dispositivo, en este caso, seleccionamos la camara trasera
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                CameraProviderx.unbindAll()
                CameraProviderx.bindToLifecycle(this@SlideshowFragment, cameraSelector,preview)
            }catch (exc: Exception){
                println("Error: "+ exc)
            }


        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, heigth: Int) {}

        override fun surfaceDestroyed(holder: SurfaceHolder) {}
    })
}



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        */
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}