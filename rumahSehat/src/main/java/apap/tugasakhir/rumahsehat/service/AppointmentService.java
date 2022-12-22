package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    List<AppointmentModel> getListAppointment();
    AppointmentModel createAppointment(AppointmentModel appt);
    AppointmentModel checkJadwalDokter(LocalDateTime waktuAwalInput, DokterModel dokter);
    AppointmentModel checkJadwalPasien(LocalDateTime waktuAwalInput, PasienModel pasien);
    AppointmentModel getAppointmentByKode(String kode);
    List<AppointmentModel> findAllByWaktuAwalInBetween(LocalDateTime awal, LocalDateTime akhir);

    List<AppointmentModel> findAllByDokterandWaktuAwalInBetween(DokterModel dokterModel,LocalDateTime awal, LocalDateTime akhir);
}
