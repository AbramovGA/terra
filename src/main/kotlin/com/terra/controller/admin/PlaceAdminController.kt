package com.terra.controller.admin

import com.terra.model.Place
import com.terra.repository.PlaceRepository
import com.terra.service.PlaceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class PlaceAdminController(@Autowired val placeService: PlaceService, @Autowired val placeRepository: PlaceRepository) {


    @GetMapping("/places")
    fun places(): List<Place> {
        return placeService.getAllPlaces()
    }

    @GetMapping("/places/{id}")
    fun place(@PathVariable("id") id: String): Place {
        return placeService.getPlaceById(id)
    }


    @PostMapping("/places")
    fun save(@RequestBody place: Place) {
        placeService.save(place)
    }

    @DeleteMapping("/places/{id}/delete")
    fun delete(@PathVariable("id") id: String) {
        placeService.delete(id)
    }
}