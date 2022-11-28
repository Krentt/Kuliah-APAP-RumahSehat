package apap.tugasAkhir.rumahSehat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class PasienModel extends UserModel{
    @Column(name = "saldo")
    private Long saldoPasien;

    @Column(name = "umur")
    private Integer umurPasien;

    @JsonManagedReference(value = "pasien")
    @OneToMany(mappedBy = "pasienModel", fetch = FetchType.LAZY)
    private List<AppointmentModel> appointmentPasien;


}
