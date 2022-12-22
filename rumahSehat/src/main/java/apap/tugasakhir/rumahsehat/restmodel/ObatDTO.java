package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.JumlahModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ObatDTO {
    private String idObat;
    private String namaObat;
    private Integer stok;
    private Integer harga;
    private List<JumlahModel> listJumlahModel;
}
