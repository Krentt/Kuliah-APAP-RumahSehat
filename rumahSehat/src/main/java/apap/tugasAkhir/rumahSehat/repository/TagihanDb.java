package apap.tugasAkhir.rumahSehat.repository;

import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.TagihanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagihanDb extends JpaRepository<TagihanModel, Long> {
    List<TagihanModel> findTagihanModelByAppointmentModel_PasienModel(PasienModel pasienModel);

    TagihanModel findById(String id);
}
