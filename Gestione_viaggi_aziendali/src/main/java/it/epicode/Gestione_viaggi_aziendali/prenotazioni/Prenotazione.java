package it.epicode.Gestione_viaggi_aziendali.prenotazioni;

import it.epicode.Gestione_viaggi_aziendali.dipendenti.Dipendente;
import it.epicode.Gestione_viaggi_aziendali.viaggi.Viaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "prenotazione",
        uniqueConstraints = @UniqueConstraint(columnNames = {"dipendente_id","data_richiesta"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataRichiesta;

    @Column(length = 500)
    private String note;
}