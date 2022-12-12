package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.ResepModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResepDb extends JpaRepository<ResepModel,Long> {
    Optional<ResepModel> findById(Long id);
}