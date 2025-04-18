package it.epicode.Gestione_viaggi_aziendali.viaggi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViaggioRequest {
    @NotBlank
    private String destinazione;
    @NotNull
    private LocalDate data;
    @NotNull
    private StatoViaggi stato;
}