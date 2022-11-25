package apap.tugasAkhir.rumahSehat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JumlahModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "obat",referencedColumnName = "id_obat")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ObatModel obat;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resep",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ResepModel resep;

    @NotNull
    @Column(nullable = false)
    private Integer kuantitas;
}
