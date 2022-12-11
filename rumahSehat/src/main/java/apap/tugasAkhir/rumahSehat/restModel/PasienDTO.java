package apap.tugasAkhir.rumahSehat.restModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasienDTO {
    private String nama;
    private String username;
    private String password;
    private String email;
    private Integer umurPasien;

}
