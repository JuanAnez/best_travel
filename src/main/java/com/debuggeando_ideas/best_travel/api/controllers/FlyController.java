package com.debuggeando_ideas.best_travel.api.controllers;

import com.debuggeando_ideas.best_travel.api.models.responses.FlyResponse;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IFlyService;
import com.debuggeando_ideas.best_travel.util.enums.SortType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(path = "fly")
@AllArgsConstructor
@Tag(name = "Fly")

public class FlyController {

    private final IFlyService flyService;
    @Operation(summary = "Return all flys")
    @GetMapping
    public ResponseEntity<Page<FlyResponse>>getAll(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestHeader (required = false)SortType sortType ){
        if (Objects.isNull(sortType))sortType = SortType.NONE;
        var response = this.flyService.realAll(page, size, sortType);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
    @Operation(summary = "Return the less price from fly")
    @GetMapping(path = "less_price")
    public ResponseEntity<Set<FlyResponse>>getLessPrice(
            @RequestParam BigDecimal price) {
        var response = this.flyService.readLessPrice(price);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
    @Operation(summary = "Return price between from flys")
    @GetMapping(path = "between_price")
    public ResponseEntity<Set<FlyResponse>>getBetweenPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        var response = this.flyService.readBetweenPrices(min, max);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
    @Operation(summary = "Return the origin and destiny from fly")
    @GetMapping(path = "origin_destiny")
    public ResponseEntity<Set<FlyResponse>>getByOriginDestiny(
            @RequestParam String origin,
            @RequestParam String destiny) {
        var response = this.flyService.readByOriginDstiny(origin, destiny);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }
    @Operation(summary = "Return the less price from fly")
    @GetMapping(path = "string")
    public String getLessPrice() {
        return "Juan";
    }
}
