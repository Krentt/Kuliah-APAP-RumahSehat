package apap.tugasAkhir.rumahSehat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointment")
@Setter
@Getter
public class AppointmentModel implements Serializable {
    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    //TODO: Generate according to soal: APT-x (APT-1, APT-2, ...)
    private String kode;

    @NotNull
    @Size(max = 50)
    @Column(name = "waktuAwal")
    private LocalDateTime waktu_awal;

    @NotNull
    @Size(max = 50)
    @Column(name="isDone")
    private boolean isDone;

    @NotNull
    @Lob
    @Column(name = "pasien")
    private String pasien_Id;

    @NotNull
    @Size(max = 50)
    @Column(name = "dokter")
    private String dokter_Id;

    // Relasi dengan resep
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id", referencedColumnName = "resep_Id") // TODO: ga yakin bener
    private ResepModel resepModel;

    // Relasi dengan tagihan
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id", referencedColumnName = "tagihan_Id") // TODO: ga yakin bener
    private TagihanModel tagihanModel;
}
