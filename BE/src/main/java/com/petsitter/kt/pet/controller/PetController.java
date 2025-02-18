package com.petsitter.kt.pet.controller;

import com.petsitter.kt.pet.controller.dto.PetCategoryListResponse;
import com.petsitter.kt.pet.service.PetService;
import com.petsitter.kt.pet.service.dto.PetCategoryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/category")
    public ResponseEntity<List<PetCategoryListResponse>> petCategoryList() {
        List<PetCategoryResult> results = petService.findAllAnimalCategories();
        return ResponseEntity.ok(results.stream().map(PetCategoryListResponse::fromResult).toList());
    }
}
