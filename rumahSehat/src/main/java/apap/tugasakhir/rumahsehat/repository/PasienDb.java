package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long> {
    PasienModel findById(String id);
    PasienModel findByUsername(String username);
}
