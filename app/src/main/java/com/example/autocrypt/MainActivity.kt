package com.example.autocrypt

import android.Manifest
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.autocrypt.data.Key
import com.example.autocrypt.data.db.AppDatabase
import com.example.autocrypt.data.db.CenterData
import com.example.autocrypt.data.response.CenterDataResponse
import com.example.autocrypt.databinding.ActivityMainBinding
import com.example.autocrypt.util.PagingAdapter
import com.example.autocrypt.viewModel.MainViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding : ActivityMainBinding
    private lateinit var naverMap : NaverMap
    private lateinit var locationSource: FusedLocationSource
    private val viewModel : MainViewModel by viewModels()
    private var markets = mutableListOf<Marker>()
    private lateinit var uiScope : CoroutineScope
    private val adapter = PagingAdapter()
    var fragment = DataFragment
    private var centerList = listOf<CenterData>()


    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000

        private val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    private fun observer(){
        lifecycleScope.launch{
            viewModel.getData().collectLatest {
                adapter.submitData(it)
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
       setContentView(binding.root)

        val mapFragment: MapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as MapFragment
        mapFragment.getMapAsync(this)

        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        uiScope = CoroutineScope(Dispatchers.Main)

        binding.moveFragment.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment.newInstance()).commit()
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    {
        Log.d("onRequest", "onRequestPermissionsResult")

        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                Log.d("권한 거부", "권한 거부됨")
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            else {
                Log.d("권한 승인", "권한 승인됨")
                naverMap.locationTrackingMode = LocationTrackingMode.Follow // 현위치 버튼 컨트롤 활성
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

        Toast.makeText(this, "맵 초기화 완료", Toast.LENGTH_LONG).show()

        //observer()
        updateMarker()

    }

    private fun updateMarker() {

        deleteMarkers()
        var markers : List<CenterDataResponse> = viewModel.getCenterData()
        var temp = arrayListOf<Marker>()
        var i = 0

        markers?.let {
            repeat(markers.size){
                temp += Marker().apply {
                    position = LatLng(markers[i].lat.toDouble(),markers[i].lng.toDouble())
                    icon = MarkerIcons.BLACK
                    tag = markers[i].centerName
                    zIndex = i
                }
                i++
            }
            markets = temp
            searchAround()
        }
    }

    private fun searchAround(){

        deleteMarkers()

        for(market in markets){
            market.map = naverMap
        }

    }

    fun deleteMarkers() {
        if (markets.isNullOrEmpty())
            return
        for (marker in markets) {
            marker.map = null
        }
    }
}