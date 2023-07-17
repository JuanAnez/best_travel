package com.debuggeando_ideas.best_travel.api.models.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourHotelRequest implements Serializable {
    @Positive
    @NotNull(message = "Id hotel is mandatory")
    public Long id;
    @Min(value = 1, message = "Min one day to make reservation")
    @Max(value = 30, message = "Max 30 days to make reservation")
    @NotNull(message = "total days is mandatory")
    private Integer totalDays;
}
