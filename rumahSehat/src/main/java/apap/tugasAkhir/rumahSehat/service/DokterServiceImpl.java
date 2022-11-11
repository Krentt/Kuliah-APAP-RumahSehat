package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokterServiceImpl implements DokterService{
    @Autowired
    DokterDb dokterDb;

    @Override
    public List<DokterModel> getListDokter() {
        return dokterDb.findAll();
    }
}
