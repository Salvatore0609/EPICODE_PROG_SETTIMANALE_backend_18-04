package it.epicode.Gestione_viaggi_aziendali;

import it.epicode.Gestione_viaggi_aziendali.dipendenti.Dipendente;
import it.epicode.Gestione_viaggi_aziendali.dipendenti.DipendenteRepository;
import it.epicode.Gestione_viaggi_aziendali.prenotazioni.Prenotazione;
import it.epicode.Gestione_viaggi_aziendali.prenotazioni.PrenotazioneRepository;
import it.epicode.Gestione_viaggi_aziendali.viaggi.StatoViaggi;
import it.epicode.Gestione_viaggi_aziendali.viaggi.Viaggio;
import it.epicode.Gestione_viaggi_aziendali.viaggi.ViaggioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataRunner implements CommandLineRunner {

    private final ViaggioRepository repo;
    private final DipendenteRepository dRepo;
    private final PrenotazioneRepository pRepo;

    @Override
    public void run(String... args) throws Exception {
        // Creazione di alcuni viaggi
        Viaggio viaggio1 = new Viaggio(null, "New York", LocalDate.of(2025, 5, 10), StatoViaggi.IN_PROGRAMMA);
        Viaggio viaggio2 = new Viaggio(null, "Londra", LocalDate.of(2025, 6, 15), StatoViaggi.IN_PROGRAMMA);
        repo.save(viaggio1);
        repo.save(viaggio2);

        // Creazione di alcuni dipendenti
        Dipendente dipendente1 = new Dipendente(null, "jdoe", "John", "Doe", "johndoe@example.com", "url-image1");
        Dipendente dipendente2 = new Dipendente(null, "asmith", "Alice", "Smith", "alicesmith@example.com", "url-image2");
        dRepo.save(dipendente1);
        dRepo.save(dipendente2);

        // Creazione di alcune prenotazioni
        Prenotazione prenotazione1 = new Prenotazione(null, viaggio1, dipendente1, LocalDate.of(2025, 5, 1), "Volo diretto, hotel vicino al centro");
        Prenotazione prenotazione2 = new Prenotazione(null, viaggio2, dipendente2, LocalDate.of(2025, 6, 1), "Preferenza per un volo economico");
        pRepo.save(prenotazione1);
        pRepo.save(prenotazione2);

        System.out.println("Dati iniziali inseriti nel database.");
    }
}
