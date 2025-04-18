package it.epicode.Gestione_viaggi_aziendali.dipendenti;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dipendenti")
@RequiredArgsConstructor
public class DipendenteController {
    private final DipendenteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponse create(@Valid @RequestBody DipendenteRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<DipendenteResponse> listAll() {
        return service.findAll();
    }

    @PostMapping(path = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DipendenteResponse uploadImage(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        return service.uploadImage(id, file);
    }
}