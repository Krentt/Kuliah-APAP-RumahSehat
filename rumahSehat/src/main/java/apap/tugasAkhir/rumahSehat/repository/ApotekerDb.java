package apap.tugasAkhir.rumahSehat.repository;

import apap.tugasAkhir.rumahSehat.model.ApotekerModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, Long> {
    Optional<ApotekerModel> findByUsername(String username);
}
