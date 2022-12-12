package apap.tugasakhir.rumahsehat.model;

import apap.tugasakhir.rumahsehat.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class TagihanModel implements Serializable {
    @Id
    //Generate according to soal: BILL-x (BILL-1, BILL-2, ...)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "seq_tagihan_id")
    @GenericGenerator(
            name="seq_tagihan_id", strategy = "apap.tugasAkhir.rumahSehat.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "0"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "BILL-")
            })
    private String id;

    @Column(name = "tanggalTerbuat")
    private LocalDateTime tanggalTerbuat;

    @Column(name = "tanggalBayar")
    private LocalDateTime tanggalBayar;

    @NotNull
    @Column(name = "isPaid")
    private Boolean isPaid;

    @NotNull
    @Column(name = "total")
    private Integer total;

    // Relasi dengan appointment
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_model_kode")
    private AppointmentModel appointmentModel;

    public void calculateTotal(){
        var totalCurr = 0;
        // If no resep then omit resep
        if(null != this.appointmentModel.getResepModel()){
            for (JumlahModel j: this.appointmentModel.getResepModel().getListJumlahModel()
            ) {
                totalCurr += j.getObat().getHarga() * j.getKuantitas();
            }
        }
        this.total = totalCurr + this.appointmentModel.getDokterModel().getTarifDokter();
    }
}
