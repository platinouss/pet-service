package com.petsitter.kt.petsitter.controller;

import com.petsitter.kt.petsitter.controller.dto.PetsitterAddRequest;
import com.petsitter.kt.petsitter.controller.dto.PetsitterDetailsResponse;
import com.petsitter.kt.petsitter.controller.dto.PetsitterReservationRequest;
import com.petsitter.kt.petsitter.controller.dto.ReservationDetailsResponse;
import com.petsitter.kt.petsitter.service.PetsitterService;
import com.petsitter.kt.petsitter.service.dto.PetsitterResult;
import com.petsitter.kt.petsitter.service.dto.ReservationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pet-sitters")
@RequiredArgsConstructor
public class PetsitterController {

    private final PetsitterService petsitterService;

    @GetMapping
    public ResponseEntity<List<PetsitterDetailsResponse>> petsitterDetails(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
        List<PetsitterResult> results = petsitterService.findPetsitters(pageable);
        return ResponseEntity.ok(results.stream().map(PetsitterDetailsResponse::fromResult).toList());
    }

    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDetailsResponse>> reservationDetails(@SessionAttribute(name = "userId") Long userId) {
        List<ReservationResult> results = petsitterService.findReservations(userId);
        return ResponseEntity.ok(results.stream().map(ReservationDetailsResponse::fromResult).toList());
    }

    @PostMapping
    public ResponseEntity<Void> petsitterAdd(@SessionAttribute(name = "userId") Long userId, @RequestBody PetsitterAddRequest request) {
        petsitterService.registerPetsitter(userId, request.toCommand());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reservation")
    public ResponseEntity<Void> petsitterReservation(@SessionAttribute(name = "userId") Long userId, @RequestBody PetsitterReservationRequest request) {
        petsitterService.createReservation(userId, request.toCommand());
        return ResponseEntity.ok().build();
    }
}
