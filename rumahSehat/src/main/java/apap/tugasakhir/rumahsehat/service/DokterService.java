package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;

import java.util.List;

public interface DokterService {
    List<DokterModel> getListDokter();
    DokterModel getDokterByUsername(String username);
    boolean checkJadwal(AppointmentModel appointmentModel);

}
