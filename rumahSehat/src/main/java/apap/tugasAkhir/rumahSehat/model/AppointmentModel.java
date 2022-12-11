package apap.tugasAkhir.rumahSehat.model;

import apap.tugasAkhir.rumahSehat.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @JoinColumn(name = "idPasien")
    private PasienModel pasienModel;

    // Relasi dengan dokter
    @JsonBackReference(value = "dokter")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "idDokter")
    private DokterModel dokterModel;

//     Relasi dengan resep

    @JsonManagedReference
    @OneToOne(mappedBy = "appointment") // TODO: ga yakin bener
    private ResepModel resepModel;

    // Relasi dengan tagihan
//    @JsonManagedReference
//    @OneToOne(cascade = CascadeType.ALL)
////    @JoinColumn(name = "id", referencedColumnName = "tagihan_Id") // TODO: ga yakin bener
//    private TagihanModel tagihanModel;
}
