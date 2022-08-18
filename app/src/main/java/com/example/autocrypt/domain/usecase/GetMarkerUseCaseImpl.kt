package com.example.autocrypt.domain.usecase

import com.naver.maps.map.overlay.Marker
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMarkerUseCaseImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher
) {

    private var markers = mutableListOf<Marker>()

}