package it.epicode.Gestione_viaggi_aziendali.prenotazioni;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {
    private final PrenotazioneService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponse create(@Valid @RequestBody PrenotazioneRequest req) {
        return service.prenota(req);
    }
}