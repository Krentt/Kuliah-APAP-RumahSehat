package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.JumlahModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;
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

    public ObatModel toModel(){
        var obatModel = new ObatModel();
        obatModel.setIdObat(this.idObat);
        obatModel.setNamaObat(this.namaObat);
        obatModel.setStok(this.stok);
        obatModel.setHarga(this.harga);
        obatModel.setListJumlahModel(this.listJumlahModel);
        return obatModel;
    }
}
