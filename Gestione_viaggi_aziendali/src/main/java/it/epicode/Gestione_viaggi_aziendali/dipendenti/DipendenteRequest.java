package it.epicode.Gestione_viaggi_aziendali.dipendenti;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DipendenteRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @Email
    @NotBlank
    private String email;
}