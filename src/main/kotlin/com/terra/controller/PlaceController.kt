package com.terra.controller

import com.terra.dto.PlaceDto
import com.terra.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PlaceController(@Autowired val placeService: PlaceService) {



    @GetMapping("/places")
    fun places(lat: Double, lng: Double, radius: Int, provider: Int): List<PlaceDto> {
        return placeService.getPlacesInRadius(lat, lng, radius, provider)
    }

    @PostMapping("/places")
    fun save(placeDto: PlaceDto) {
        placeService.save(placeDto)
    }
}