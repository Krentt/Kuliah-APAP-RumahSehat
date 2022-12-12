package apap.tugasakhir.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class ApotekerModel extends UserModel{

    //relasi dengan resepModel
    @JsonManagedReference(value = "apoteker")
    @OneToMany(mappedBy = "apotekerModel", fetch = FetchType.LAZY)
    private List<ResepModel> resepApoteker;

}
