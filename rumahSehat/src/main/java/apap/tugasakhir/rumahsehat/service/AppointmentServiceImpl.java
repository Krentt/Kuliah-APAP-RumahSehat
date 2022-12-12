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
    public AppointmentModel createAppointent(AppointmentModel appt) {
        return appointmentDb.save(appt);
    }

    @Override
    public AppointmentModel checkJadwalDokter(LocalDateTime waktuAwalInput, DokterModel dokter) {

        for (AppointmentModel appt : dokter.getAppointmentDokter()) {
            if (checkWaktu(waktuAwalInput, appt)) return appt;
        }

        return null;
    }

    @Override
    public AppointmentModel checkJadwalPasien(LocalDateTime waktuAwalInput, PasienModel pasien) {

        for (AppointmentModel apptPasien : pasien.getAppointmentPasien()) {
            if (checkWaktu(waktuAwalInput, apptPasien)) return apptPasien;

        }
        return null;
    }

    private boolean checkWaktu(LocalDateTime waktuAwalInput, AppointmentModel apptPasien) {
        LocalDateTime waktuAwalPasien = apptPasien.getWaktuAwal();
        LocalDateTime waktuAkhirPasien = apptPasien.getWaktuAwal().plusHours(1L);
        if (waktuAwalInput.isEqual(waktuAwalPasien)){
            return true;
        } else {
            if (waktuAwalInput.isAfter(waktuAwalPasien) && waktuAwalInput.isBefore(waktuAkhirPasien)) {
                return true;
            }
        }

        return waktuAwalInput.plusHours(1L).isAfter(waktuAwalPasien) && waktuAwalInput.plusHours(1L).isBefore(waktuAkhirPasien);
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
