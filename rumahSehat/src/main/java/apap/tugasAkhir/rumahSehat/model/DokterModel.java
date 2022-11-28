package apap.tugasAkhir.rumahSehat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class DokterModel extends UserModel{
    @Column(name="tarif")
    private int tarifDokter;

    @JsonManagedReference(value = "dokter")
    @OneToMany(mappedBy = "dokterModel", fetch = FetchType.LAZY)
    private List<AppointmentModel> appointmentDokter;

}
