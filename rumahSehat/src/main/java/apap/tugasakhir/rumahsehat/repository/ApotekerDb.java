package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, Long> {
    Optional<ApotekerModel> findByUsername(String username);
}
