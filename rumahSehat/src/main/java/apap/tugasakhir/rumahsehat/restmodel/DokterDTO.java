package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.RoleModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DokterDTO {
    private String nama;
    private String username;
    private String password;
    private String email;
    private int tarifDokter;
    private RoleModel role;

}
