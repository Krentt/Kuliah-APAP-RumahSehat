package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ResepModel;

import java.util.List;

public interface ResepService {

    ResepModel addResep(ResepModel resep);

    List<ResepModel> getListResep();

    ResepModel getResepByIdResep (Long idResep);
}