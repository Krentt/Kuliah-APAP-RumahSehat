package apap.tugasAkhir.rumahSehat.repository;

import apap.tugasAkhir.rumahSehat.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
