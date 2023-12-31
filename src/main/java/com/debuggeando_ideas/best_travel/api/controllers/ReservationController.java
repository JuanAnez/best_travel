package com.debuggeando_ideas.best_travel.api.controllers;

import com.debuggeando_ideas.best_travel.api.models.request.ReservationRequest;
import com.debuggeando_ideas.best_travel.api.models.responses.ErrorResponse;
import com.debuggeando_ideas.best_travel.api.models.responses.ReservationResponse;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping(path = "reservation")
@AllArgsConstructor
@Tag(name = "Reservation")
public class ReservationController {

    private final IReservationService reservationService;

    @ApiResponse(
            responseCode = "400",
            description = "When the request have a field valid with response this",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            }
    )
    @Operation(summary = "Save in system one reservation")
    @PostMapping
    public ResponseEntity<ReservationResponse> post(@Valid @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(reservationService.create(request));
    }

    @Operation(summary = "Return one reservation with passed Id")
    @GetMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> get(@PathVariable UUID id) {
        return ResponseEntity.ok(reservationService.read(id));
    }

    @Operation(summary = "Update one reservation with passed Id")
    @PutMapping(path = "{id}")
    public ResponseEntity<ReservationResponse> put(@Valid @PathVariable UUID id, @RequestBody ReservationRequest request) {
        return ResponseEntity.ok(this.reservationService.update(request, id));
    }

    @Operation(summary = "Delete one reservation with id passed")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Return one reservation with passed hotel price Id")
    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> getReservation(
            @RequestParam Long hotelId,
            @RequestHeader(required = false) Currency currency) {
        if (Objects.isNull(currency)) currency = Currency.getInstance("USD");
        return ResponseEntity.ok(Collections.singletonMap("hotelPrice", this.reservationService.findPrice(hotelId, currency)));
    }

}
