package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;

import java.util.List;

public interface PasienService  {
    //Cisco: Feat14
    PasienModel getPasienById(String id);
    PasienModel getPasienByUsername(String username);
    List<PasienModel> getAllPasien();

    List<AppointmentModel> getPasienAppointment(String username);

    PasienModel updatePasienSaldo(PasienModel pasienModel);
}
