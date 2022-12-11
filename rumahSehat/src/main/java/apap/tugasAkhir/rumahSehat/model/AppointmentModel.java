package apap.tugasAkhir.rumahSehat.model;

import apap.tugasAkhir.rumahSehat.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Setter
@Getter
public class AppointmentModel implements Serializable {
    @Id
    //Generate according to soal: APT-x (APT-1, APT-2, ...)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "appoint_id")
    @GenericGenerator(
            name="appoint_id", strategy = "apap.tugasAkhir.rumahSehat.util.StringPrefixedSequenceIdGenerator",
            parameters = {
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "0"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "APT-")
    })
    private String kode;

    @NotNull
    @Column(name = "waktuAwal")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime waktuAwal;

    @NotNull
    @Column(name="isDone")
    private boolean isDone;

    // Relasi dengan pasien
    @JsonBackReference(value = "pasien")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_pasien")
    private PasienModel pasienModel;

    // Relasi dengan dokter
    @JsonBackReference(value = "dokter")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_dokter")
    private DokterModel dokterModel;

//     Relasi dengan resep
    @JsonManagedReference
    @OneToOne(mappedBy = "appointment") // TODO: ga yakin bener
    private ResepModel resepModel;
}
