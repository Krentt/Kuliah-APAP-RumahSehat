package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.ApotekerModel;
import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;
import apap.tugasAkhir.rumahSehat.repository.ApotekerDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApotekerServiceImpl implements ApotekerService{
    @Autowired
    ApotekerDb apotekerDb;

    @Override
    public List<ApotekerModel> getListApoteker() {
        return apotekerDb.findAll();
    }

    @Override
    public ApotekerModel getApotekerByUsername(String username){
        Optional<ApotekerModel> apoteker = apotekerDb.findByUsername(username);
        if (apoteker.isPresent()){
            return apoteker.get();
        }
        else{
            return null;
        }
    }
}
