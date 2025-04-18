package it.epicode.Gestione_viaggi_aziendali.viaggi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/viaggi")
@RequiredArgsConstructor
public class ViaggioController {
    private final ViaggioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioResponse create(@Valid @RequestBody ViaggioRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<ViaggioResponse> listAll() {
        return service.findAll();
    }

    @PatchMapping("/{id}/stato")
    public ViaggioResponse changeState(
            @PathVariable Long id,
            @RequestParam StatoViaggi stato
    ) {
        return service.updateState(id, stato);
    }
}