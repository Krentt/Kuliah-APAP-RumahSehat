package apap.tugasAkhir.rumahSehat.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class PasienModel extends UserModel{
    @Column(name = "saldo")
    private Long saldoPasien;

    @Column(name = "umur")
    private Integer umurPasien;


}
