package apap.tugasAkhir.rumahSehat.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
public class DokterModel extends UserModel{
    @Column(name="tarif")
    private int tarifDokter;

}
