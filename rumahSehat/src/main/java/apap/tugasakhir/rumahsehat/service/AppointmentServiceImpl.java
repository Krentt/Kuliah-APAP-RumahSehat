package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.repository.AppointmentDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    AppointmentDb appointmentDb;

    @Override
    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }

    @Override
    public AppointmentModel createAppointment(AppointmentModel appt) {
        return appointmentDb.save(appt);
    }

    /**
     * Mencari Appointment pada waktu tertenu
     * @param waktuAwalInput waktu appointment dimulai yang dicari
     * @param dokter yang memilikki appointment
     * @return appt yang mulai pada waktuAwalInput or NULL if not found
     */
    @Override
    public AppointmentModel checkJadwalDokter(LocalDateTime waktuAwalInput, DokterModel dokter) {

        for (AppointmentModel appt : dokter.getAppointmentDokter()) {
            if (checkWaktu(waktuAwalInput, appt)) return appt;
        }

        return null;
    }

    /**
     * Mencari Appointment pada waktu tertenu
     * @param waktuAwalInput waktu appointment dimulai yang dicari
     * @param pasien yang memilikki appointment
     * @return appt yang mulai pada waktuAwalInput or NULL if not found
     */
    @Override
    public AppointmentModel checkJadwalPasien(LocalDateTime waktuAwalInput, PasienModel pasien) {

        for (AppointmentModel apptPasien : pasien.getAppointmentPasien()) {
            if (checkWaktu(waktuAwalInput, apptPasien)) return apptPasien;

        }
        return null;
    }

    private boolean checkWaktu(LocalDateTime waktuAwalInput, AppointmentModel appointmentModel) {
        LocalDateTime waktuAwal = appointmentModel.getWaktuAwal();
        LocalDateTime waktuAkhir = appointmentModel.getWaktuAwal().plusHours(1L);
        if (waktuAwalInput.isEqual(waktuAwal)){
            return true;
        } else {
            if (waktuAwalInput.isAfter(waktuAwal) && waktuAwalInput.isBefore(waktuAkhir)) {
                return true;
            }
        }

        return waktuAwalInput.plusHours(1L).isAfter(waktuAwal) &&
                waktuAwalInput.plusHours(1L).isBefore(waktuAkhir);
    }

    @Override
    public AppointmentModel getAppointmentByKode(String kode) {
        return appointmentDb.getAppointmentModelByKode(kode);
    }

    @Override
    public List<AppointmentModel> findAllByWaktuAwalInBetween(LocalDateTime awal, LocalDateTime akhir) {
        return appointmentDb.findAllByWaktuAwalBetween(awal, akhir);
    }

    @Override
    public List<AppointmentModel> findAllByDokterandWaktuAwalInBetween(DokterModel dokterModel, LocalDateTime awal, LocalDateTime akhir) {
        return  appointmentDb.findAllByDokterModelUsernameAndWaktuAwalBetween(dokterModel.getUsername(),awal,akhir);
    }
}
