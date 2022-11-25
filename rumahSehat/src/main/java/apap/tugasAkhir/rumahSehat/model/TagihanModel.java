package apap.tugasAkhir.rumahSehat.model;

import apap.tugasAkhir.rumahSehat.model.JumlahModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class TagihanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotNull
    @Size(max = 50)
    @Column(name = "kodeAppointment")
    private String kode_appointment;

    //For Dummy Testing, TODO: REMOVE when apppointment is done
    private ResepModel resep;
    private int totalResep;
    private int tarifDokter;

    public void calculateTotal(){
        int totalCurr = 0;

        for (JumlahModel j: this.resep.getListJumlahModel()
             ) {
            totalCurr += j.getObat().getHarga() * j.getKuantitas();
        }
        this.totalResep = totalCurr;
        this.total = totalResep + this.tarifDokter;
    }
}
