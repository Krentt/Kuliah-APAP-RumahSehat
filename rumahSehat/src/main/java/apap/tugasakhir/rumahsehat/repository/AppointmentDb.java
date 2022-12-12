package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, Long> {
    AppointmentModel getAppointmentModelByKode(String kode);

    List<AppointmentModel> findAllByWaktuAwalBetween(LocalDateTime awal, LocalDateTime akhir);

    List<AppointmentModel> findAllByDokterModel_UsernameAndWaktuAwalBetween(String username,LocalDateTime awal, LocalDateTime akhir);
}
