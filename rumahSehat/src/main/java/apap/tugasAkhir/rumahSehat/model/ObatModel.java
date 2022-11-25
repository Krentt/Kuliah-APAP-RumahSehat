package apap.tugasAkhir.rumahSehat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ObatModel implements Serializable {
    @Id
    @Column(name = "id_obat")
    private String idObat;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_obat",nullable = false)
    private String namaObat;

    @NotNull
    @Column(columnDefinition = "integer default 100",nullable = false)
    private Integer stok;

    @NotNull
    @Column(nullable = false)
    private Integer harga;

    // Relasi dengan resep
    @JsonManagedReference
    @OneToMany(mappedBy = "obat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<JumlahModel> listJumlahModel;

    //Relasi dengan obat
}
