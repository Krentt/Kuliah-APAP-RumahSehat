package apap.tugasAkhir.rumahSehat.repository;

import apap.tugasAkhir.rumahSehat.model.ResepModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResepDb extends JpaRepository<ResepModel,Long> {
    Optional<ResepModel> findById(Long id);
}