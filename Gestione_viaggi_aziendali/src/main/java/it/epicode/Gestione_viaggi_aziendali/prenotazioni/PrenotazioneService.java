package it.epicode.Gestione_viaggi_aziendali.prenotazioni;

import it.epicode.Gestione_viaggi_aziendali.common.BookingConflictException;
import it.epicode.Gestione_viaggi_aziendali.dipendenti.Dipendente;
import it.epicode.Gestione_viaggi_aziendali.dipendenti.DipendenteRepository;
import it.epicode.Gestione_viaggi_aziendali.viaggi.Viaggio;
import it.epicode.Gestione_viaggi_aziendali.viaggi.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {
    private final PrenotazioneRepository repo;
    private final ViaggioRepository vRepo;
    private final DipendenteRepository dRepo;

    public PrenotazioneResponse prenota(PrenotazioneRequest req) {
        Dipendente d = dRepo.findById(req.getDipendenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        if (repo.existsByDipendenteAndDataRichiesta(d, req.getDataRichiesta())) {
            throw new BookingConflictException("Dipendente impegnato in quella data");
        }
        Viaggio v = vRepo.findById(req.getViaggioId())
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        Prenotazione p = new Prenotazione();
        p.setDipendente(d);
        p.setViaggio(v);
        p.setDataRichiesta(req.getDataRichiesta());
        p.setNote(req.getNote());
        repo.save(p);
        return new PrenotazioneResponse(p.getId(), v.getId(), d.getId(), p.getDataRichiesta(), p.getNote());
    }
}