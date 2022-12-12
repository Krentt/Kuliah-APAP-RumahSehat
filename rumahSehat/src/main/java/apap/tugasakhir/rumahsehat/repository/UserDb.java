package apap.tugasakhir.rumahsehat.repository;

import apap.tugasakhir.rumahsehat.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
