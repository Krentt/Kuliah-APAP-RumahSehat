package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.TagihanModel;
import apap.tugasAkhir.rumahSehat.model.UserModel;

import java.util.List;

public interface PasienService {
    //Cisco: Feat14
    PasienModel getPasienById(String id);
    PasienModel getPasienByUsername(String Username);
    List<PasienModel> getAllPasien();

    List<AppointmentModel> getPasienAppointment(String Username);

    List<TagihanModel> getPasienTagihan(String Username);
}
