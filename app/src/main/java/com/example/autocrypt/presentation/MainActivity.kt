package com.example.autocrypt.presentation

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.autocrypt.R
import com.example.autocrypt.data.entity.CenterDataEntity
import com.example.autocrypt.databinding.ActivityMainBinding
import com.example.autocrypt.widget.PagingAdapter
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var naverMap: NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val viewModel: MainViewModel by viewModels()
    private var markers = mutableListOf<Marker>()
    private lateinit var uiScope: CoroutineScope
    lateinit var adatper: PagingAdapter
    lateinit var markerEntity: List<CenterDataEntity>
    private var infoWindow: InfoWindow? = null


    private var job: Deferred<Unit>? = null

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000

        private val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    private fun submitData() = with(viewModel) {
        lifecycleScope.launch(Dispatchers.Main) {
            getData().collectLatest {
                adatper.submitData(it)
            }
        }
    }

    private fun observerData() {
        viewModel.centers.observe(this@MainActivity) {
            when (it) {
                is MainState.Loading -> lifecycleScope.launch(Dispatchers.IO) {
                    job = viewModel.getRoomData()
                    showProgress()
                }
                    is MainState.Success -> {

                }
            }
        }
    }

    private fun showProgress() = lifecycleScope.launch() {
        binding.progressBar.visibility = View.VISIBLE

        for (progress in 0..100) {
            binding.progressBar.progress = progress
            delay(20)
            if (progress == 80) {
                job?.await()
            }
        }

        binding.progressBar.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiScope = CoroutineScope(Dispatchers.Main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adatper = PagingAdapter()

        submitData()
        observerData()


        val mapFragment: MapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        mapFragment.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)


        binding.moveFragment.setOnClickListener {
            mapFragment.getMapAsync(this)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {
                naverMap.locationTrackingMode = LocationTrackingMode.None
            } else {
                naverMap.locationTrackingMode = LocationTrackingMode.Follow
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapReady(map: NaverMap) {
        this.naverMap = map

        naverMap.uiSettings.isLocationButtonEnabled = true
        naverMap.locationSource = locationSource

        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE)

        updateMarker()
    }

    private fun updateMarker() {
        deleteMarkers()
        var temp = arrayListOf<Marker>()
        markerEntity = viewModel.getRoomList()!!
        var i = 0
        markerEntity?.let {
            repeat(markerEntity.size) {
                temp += Marker().apply {
                    position =
                        LatLng(markerEntity[i].lat.toDouble(), markerEntity[i].lng.toDouble())
                    if (markerEntity[i].centerType == "중앙/권역") {
                        icon = MarkerIcons.BLACK
                    } else {
                        icon = MarkerIcons.RED
                    }
                    tag = markerEntity[i]
                    zIndex = i
                }
                i++
            }
            markers = temp
            searchAround()
            setMarkerListener()
        }
    }

    private fun searchAround() {
        deleteMarkers()
        for (market in markers) {
            market.map = naverMap
        }

    }

    fun deleteMarkers() {
        if (markers.isNullOrEmpty())
            return
        for (marker in markers) {
            marker.map = null
        }
    }

    private fun setMarkerListener() {
        for (marker in markers) {

            var tempinfoWindow = InfoWindow()
            tempinfoWindow?.adapter = object : InfoWindow.DefaultTextAdapter(this) {
                override fun getText(infoWindow: InfoWindow): CharSequence {
                    return (infoWindow.marker?.tag as CenterDataEntity).centerName
                }
            }
            infoWindow = tempinfoWindow
            marker.setOnClickListener {
                if (tempinfoWindow?.marker != null) {
                    tempinfoWindow?.close()
                    binding.centerInfo.visibility = View.GONE
                } else {
                    tempinfoWindow?.open(marker)
                    val cameraUpdate = CameraUpdate.scrollTo(marker.position)
                    naverMap.moveCamera(cameraUpdate)

                    binding.centerInfo.visibility = View.VISIBLE
                    binding.center = marker.tag as CenterDataEntity
                }
                true
            }
        }
    }


}