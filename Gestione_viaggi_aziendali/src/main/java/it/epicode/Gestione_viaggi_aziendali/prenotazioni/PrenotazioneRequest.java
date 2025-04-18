package it.epicode.Gestione_viaggi_aziendali.prenotazioni;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneRequest {
    @NotNull
    private Long viaggioId;
    @NotNull
    private Long dipendenteId;
    @NotNull
    private LocalDate dataRichiesta;
    private String note;
}