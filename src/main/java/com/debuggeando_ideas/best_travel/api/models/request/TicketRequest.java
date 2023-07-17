package com.debuggeando_ideas.best_travel.api.models.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketRequest implements Serializable {
    @Size(min = 18,max = 20, message = "The size must be between 18 and 20 characters long.")
    @NotBlank(message = "Id client is mandatory")
    private String idClient;
    @Positive
    @NotNull(message = "Id fly is mandatory")
    private Long idFly;
    @Email(message = "Invalid email")
    private String email;
}
