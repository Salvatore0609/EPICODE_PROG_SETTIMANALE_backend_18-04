package it.epicode.Gestione_viaggi_aziendali.prenotazioni;

import it.epicode.Gestione_viaggi_aziendali.dipendenti.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteAndDataRichiesta(Dipendente d, LocalDate data);
}