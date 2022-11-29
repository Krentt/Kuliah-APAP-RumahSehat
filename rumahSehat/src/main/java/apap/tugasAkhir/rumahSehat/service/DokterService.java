package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.DokterModel;

import java.util.List;

public interface DokterService {
    List<DokterModel> getListDokter();
    DokterModel getDokterByUsername(String username);
    boolean checkJadwal(AppointmentModel appointmentModel);

}
