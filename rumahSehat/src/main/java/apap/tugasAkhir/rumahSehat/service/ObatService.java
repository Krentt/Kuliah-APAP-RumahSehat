package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.ObatModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;

import java.util.List;

public interface ObatService {
    List<ObatModel> getListObat();
    ObatModel getObatById(String idObat);
    ObatModel updateObat(ObatModel obat);
    void addObat(ObatModel obat);
}
