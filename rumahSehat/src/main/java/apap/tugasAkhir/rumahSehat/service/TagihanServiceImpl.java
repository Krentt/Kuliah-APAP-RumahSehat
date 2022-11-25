package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.RoleModel;
import apap.tugasAkhir.rumahSehat.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagihanServiceImpl implements TagihanService{

    @Override
    public PasienModel getTagihanbyResepId(String id) {
        return null;
    }
}
