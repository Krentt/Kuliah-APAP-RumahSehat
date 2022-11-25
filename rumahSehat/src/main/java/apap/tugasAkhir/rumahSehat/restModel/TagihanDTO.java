package apap.tugasAkhir.rumahSehat.restModel;

import apap.tugasAkhir.rumahSehat.model.JumlahModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagihanDTO {
    private String id;
    private Long total;
    private ResepModel resep;
    private Long totalResep;
    private Long tarifDokter;

    private void calculateTotal(){
        long totalCurr = 0L;

        for (JumlahModel j: this.resep.getListObat()
             ) {
            totalCurr += (long) j.getObat().getHarga() * j.getKuantitas();
        }
        this.totalResep = totalCurr;
        this.total = totalResep + this.tarifDokter;
    }
}
