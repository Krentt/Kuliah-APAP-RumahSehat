package apap.tugasakhir.rumahsehat.restmodel;

import lombok.Data;

@Data
public class PasienDTO {
    private String nama;
    private String username;
    private String password;
    private String email;
    private Integer umurPasien;

}
