package it.epicode.Gestione_viaggi_aziendali.prenotazioni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneResponse {
    private Long id;
    private Long viaggioId;
    private Long dipendenteId;
    private LocalDate dataRichiesta;
    private String note;
}