package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.UserModel;
import apap.tugasAkhir.rumahSehat.repository.PasienDb;
import apap.tugasAkhir.rumahSehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PasienServiceImpl implements PasienService{
    @Autowired
    private PasienDb pasienDb;


    /** Cisco:Feature 14
     * Gets pasien berdasarkan id
     * @param id pasien yang dicari
     * @return pasienmodel yang memilikki id (param)
     */
    @Override
    public PasienModel getPasienById(String id) {
        return pasienDb.findById(id);
    }
}
