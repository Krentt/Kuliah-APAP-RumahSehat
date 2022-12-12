package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.JumlahModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JumlahObatDb extends JpaRepository<JumlahModel,Long> {

}