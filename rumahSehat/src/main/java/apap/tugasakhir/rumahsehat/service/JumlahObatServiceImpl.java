package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.JumlahModel;
import apap.tugasakhir.rumahsehat.repository.JumlahObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JumlahObatServiceImpl implements JumlahObatService {
    @Autowired
    JumlahObatDb jumlahObatDb;

    @Override
    public List<JumlahModel> getListJumlahObat() {
        return jumlahObatDb.findAll();
    }
}
