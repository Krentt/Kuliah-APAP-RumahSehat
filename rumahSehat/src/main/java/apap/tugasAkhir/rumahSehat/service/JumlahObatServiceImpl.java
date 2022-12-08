package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.JumlahModel;
import apap.tugasAkhir.rumahSehat.repository.JumlahObatDb;
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
