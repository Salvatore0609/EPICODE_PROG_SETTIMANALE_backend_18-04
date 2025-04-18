package it.epicode.Gestione_viaggi_aziendali.dipendenti;

import com.cloudinary.Cloudinary;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DipendenteService {
    private final DipendenteRepository repo;
    private final Cloudinary cloudinary;

    public DipendenteResponse create(DipendenteRequest req) {
        Dipendente d = new Dipendente();
        BeanUtils.copyProperties(req, d);
        repo.save(d);
        return new DipendenteResponse(d.getId(), d.getUsername(), d.getNome(), d.getCognome(), d.getEmail(), d.getProfileImageUrl());
    }

    public List<DipendenteResponse> findAll() {
        return repo.findAll().stream()
                .map(d -> new DipendenteResponse(
                        d.getId(), d.getUsername(), d.getNome(), d.getCognome(), d.getEmail(), d.getProfileImageUrl()
                ))
                .toList();
    }

    public DipendenteResponse uploadImage(Long id, MultipartFile file) throws IOException {
        Dipendente d = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        Map<?,?> res = cloudinary.uploader().upload(file.getBytes(),
                Cloudinary.asMap("folder","dipendenti","public_id",d.getUsername())
        );
        String url = res.get("secure_url").toString();
        d.setProfileImageUrl(url);
        repo.save(d);
        return new DipendenteResponse(d.getId(), d.getUsername(), d.getNome(), d.getCognome(), d.getEmail(), url);
    }
}