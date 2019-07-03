package com.terra.service

import com.terra.apis.placeApi.amadeus.AmadeusPlaceApi
import com.terra.model.Place
import com.terra.model.PlaceProvider.AMADEUS
import com.terra.model.PlaceProvider.MONGO
import com.terra.repository.PlaceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlaceService(@Autowired val placeRepository: PlaceRepository, @Autowired val amadeusApi: AmadeusPlaceApi) {

    fun getPlacesInRadius(lat: Double, lng: Double, radius: Int, provider: Int = -1): List<Place> {
        return when (provider) {
            MONGO.value -> getMongoPlacesInRadius(lat, lng, radius)
            AMADEUS.value -> getAmadeusPlacesInRadius(lat, lng, radius)
            else -> getAllPlacesInRadius(lat, lng, radius)
        }
    }

    fun getAllPlacesInRadius(lat: Double, lng: Double, radius: Int): List<Place> {
        val places = mutableListOf<Place>()

        places.addAll(getAmadeusPlacesInRadius(lat, lng, radius))

        places.addAll(getMongoPlacesInRadius(lat, lng, radius))

        return places
    }

    private fun getMongoPlacesInRadius(lat: Double, lng: Double, radius: Int): List<Place> {
        return placeRepository.findAllByLatitudeBetweenAndLongitudeBetween(
                lat - radius,
                lat + radius,
                lng - radius,
                lng + radius
        )
    }

    fun getAmadeusPlacesInRadius(lat: Double, lng: Double, radius: Int): List<Place> {
        return amadeusApi.places(lat, lng, radius)
    }
}