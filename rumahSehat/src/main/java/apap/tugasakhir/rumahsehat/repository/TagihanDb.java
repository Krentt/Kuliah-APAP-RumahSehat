package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, Long> {
    List<TagihanModel> findTagihanModelByAppointmentModel_PasienModel(PasienModel pasienModel);

    TagihanModel findById(String id);
}
