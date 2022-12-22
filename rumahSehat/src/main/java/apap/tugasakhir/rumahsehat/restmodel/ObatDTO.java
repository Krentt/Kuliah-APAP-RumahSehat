package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.JumlahModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;
import lombok.Data;

import java.util.List;

@Data
public class ObatDTO {
    private String idObat;
    private String namaObat;
    private Integer stok;
    private Integer harga;
    private List<JumlahModel> listJumlahModel;

    public ObatDTO(String idObat, String namaObat, Integer stok, Integer harga, List<JumlahModel> listJumlahModel) {
        this.idObat = idObat;
        this.namaObat = namaObat;
        this.stok = stok;
        this.harga = harga;
        this.listJumlahModel = listJumlahModel;
    }

    public ObatModel toModel() {
        var obatModel = new ObatModel();
        obatModel.setIdObat(this.idObat);
        obatModel.setNamaObat(this.namaObat);
        obatModel.setStok(this.stok);
        obatModel.setHarga(this.harga);
        obatModel.setListJumlahModel(this.listJumlahModel);
        return obatModel;
    }
}
