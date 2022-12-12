package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.repository.ApotekerDb;
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
        return apoteker.orElse(null);
    }
}
