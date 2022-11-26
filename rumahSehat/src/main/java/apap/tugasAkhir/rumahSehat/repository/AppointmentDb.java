package apap.tugasAkhir.rumahSehat.repository;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, Long> {
}
