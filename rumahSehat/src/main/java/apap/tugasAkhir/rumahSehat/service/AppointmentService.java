package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    List<AppointmentModel> getListAppointment();
    AppointmentModel createAppointent(AppointmentModel appt);
    AppointmentModel checkJadwalDokter(LocalDateTime waktuAwalInput, DokterModel dokter);
    AppointmentModel checkJadwalPasien(LocalDateTime waktuAwalInput, PasienModel pasien);
    AppointmentModel getAppointmentByKode(String kode);
}
