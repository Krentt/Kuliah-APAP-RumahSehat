package apap.tugasAkhir.rumahSehat.model;

import apap.tugasAkhir.rumahSehat.model.JumlahModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class TagihanModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //TODO: Generate according to soal: BILL-x (BILL-1, BILL-2, ...)
    private String id;

    @NotNull
    @Size(max = 50)
    @Column(name = "tanggalTerbuat")
    private LocalDateTime tanggalTerbuat;

    @NotNull
    @Size(max = 50)
    @Column(name = "tanggalBayar")
    private LocalDateTime tanggalBayar;

    @NotNull
    @Size(max = 50)
    @Column(name = "isPaid")
    private Boolean isPaid;

    @NotNull
    @Size(max = 50)
    @Column(name = "total")
    private Integer total;

//    @NotNull
//    @Size(max = 50)
//    @Column(name = "kodeAppointment")
//    private String kode_appointment;

    // Relasi dengan appointment
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kode", referencedColumnName = "kodeAppointment") // TODO: ga yakin bener
    private AppointmentModel appointmentModel;

    //For Dummy Testing, TODO: REMOVE when apppointment is done
//    private ResepModel resep;
    private int totalResep;
    private int tarifDokter;

    public void calculateTotal(){
        int totalCurr = 0;

        for (JumlahModel j: this.appointmentModel.getResepModel().getListJumlahModel()
             ) {
            totalCurr += j.getObat().getHarga() * j.getKuantitas();
        }
        this.totalResep = totalCurr;
        this.total = totalResep + this.tarifDokter;
    }
}
