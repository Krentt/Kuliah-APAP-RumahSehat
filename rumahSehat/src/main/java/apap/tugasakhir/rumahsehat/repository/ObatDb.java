package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObatDb extends JpaRepository<ObatModel,String> {
    ObatModel findByIdObat(String idObat);
}
