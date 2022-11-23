package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.ResepModel;

import java.util.List;

public interface ResepService {

    void addResep(ResepModel resep);

    List<ResepModel> getListResep();
}