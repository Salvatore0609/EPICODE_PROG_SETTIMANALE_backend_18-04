package it.epicode.Gestione_viaggi_aziendali.viaggi;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViaggioService {

    private final ViaggioRepository repo;

    public ViaggioResponse create(ViaggioRequest req) {
        Viaggio v = new Viaggio();
        BeanUtils.copyProperties(req, v);
        repo.save(v);
        return new ViaggioResponse(v.getId(), v.getDestinazione(), v.getData(), v.getStato());
    }

    public List<ViaggioResponse> findAll() {
        return repo.findAll().stream()
                .map(v -> new ViaggioResponse(
                        v.getId(), v.getDestinazione(), v.getData(), v.getStato()
                ))
                .toList();
    }

    public ViaggioResponse updateState(Long id, StatoViaggi stato) {
        Viaggio v = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        v.setStato(stato);
        repo.save(v);
        return new ViaggioResponse(v.getId(), v.getDestinazione(), v.getData(), v.getStato());
    }
}