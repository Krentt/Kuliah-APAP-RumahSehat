package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.repository.ResepDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResepServiceImpl implements ResepService {
    @Autowired
    ResepDb resepDb;

    @Override
    public ResepModel addResep(ResepModel resep) {
        return resepDb.save(resep);
    }

    @Override
    public List<ResepModel> getListResep() {
        return resepDb.findAll();
    }

    @Override
    public ResepModel getResepByIdResep(Long idResep) {
        Optional<ResepModel> resep = resepDb.findById(idResep);
        return resep.orElse(null);
    }

}
