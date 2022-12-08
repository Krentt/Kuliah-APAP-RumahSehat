package apap.tugasAkhir.rumahSehat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResepModel implements Serializable {
    @Id
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Boolean isDone =false;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createdAt;

    // Relasi dengan obat
    @JsonManagedReference
    @OneToMany(mappedBy = "resep", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JumlahModel> listJumlahModel;

    // Relasi dengan appointment
    @JsonBackReference
    @OneToOne(mappedBy = "resepModel")
    private AppointmentModel appointment;

}
