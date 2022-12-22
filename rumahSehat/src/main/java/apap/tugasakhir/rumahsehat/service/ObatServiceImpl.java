package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObatServiceImpl implements ObatService {
    @Autowired
    ObatDb obatDb;

    @Override
    public List<ObatModel> getListObat() {
        return obatDb.findAll();
    }

    @Override
    public ObatModel getObatById(String idObat) {
        return obatDb.findByIdObat(idObat);
    }

    @Override
    public ObatModel updateObat(ObatModel obat) {
        return obatDb.save(obat);
    }

    @Override
    public ObatModel addObat(ObatModel obat) {
        return obatDb.save(obat);
    }
}
