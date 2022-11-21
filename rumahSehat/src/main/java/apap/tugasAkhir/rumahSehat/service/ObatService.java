package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.ObatModel;

import java.util.List;

public interface ObatService {
    List<ObatModel> getListObat();
    ObatModel getObatById(String idObat);
    ObatModel updateObat(ObatModel obat);
}
