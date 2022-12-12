package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, Long>, Serializable {
    PasienModel findById(String id);
    PasienModel findByUsername(String username);
}
