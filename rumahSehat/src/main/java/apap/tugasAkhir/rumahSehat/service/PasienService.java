package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;

import java.util.List;

public interface PasienService  {
    //Cisco: Feat14
    PasienModel getPasienById(String id);
    PasienModel getPasienByUsername(String username);
    List<PasienModel> getAllPasien();

    List<AppointmentModel> getPasienAppointment(String username);

    PasienModel updatePasienSaldo(PasienModel pasienModel);
}
