package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.UserModel;

public interface PasienService {
    //Cisco: Feat14
    PasienModel getPasienById(String id);
    PasienModel getPasienByUsername(String Username);
}
