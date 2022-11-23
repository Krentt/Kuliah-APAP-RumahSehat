package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.ResepModel;
import apap.tugasAkhir.rumahSehat.repository.ResepDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResepServiceImpl implements ResepService {
    @Autowired
    ResepDb resepDb;

    @Override
    public void addResep(ResepModel resep){
        resepDb.save(resep);
    }

    @Override
    public List<ResepModel> getListResep() {
        return resepDb.findAll();
    }
}
