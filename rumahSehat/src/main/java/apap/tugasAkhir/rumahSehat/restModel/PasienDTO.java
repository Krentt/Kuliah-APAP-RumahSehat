package apap.tugasAkhir.rumahSehat.restModel;

import apap.tugasAkhir.rumahSehat.model.RoleModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PasienDTO {
    private String nama;
    private String username;
    private String password;
    private String email;
    private Integer umurPasien;

}
