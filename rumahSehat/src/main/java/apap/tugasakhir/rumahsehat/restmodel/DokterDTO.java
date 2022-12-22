package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.RoleModel;
import apap.tugasakhir.rumahsehat.model.UserModel;
import lombok.Data;

@Data
public class DokterDTO {
    private String nama;
    private String username;
    private String password;
    private String email;
    private int tarifDokter;
    private RoleModel role;

    public DokterDTO(String nama, String username, String password, String email, int tarifDokter, RoleModel role) {
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tarifDokter = tarifDokter;
        this.role = role;
    }


    public UserModel toUserModel(){
        var user = new DokterModel();
        user.setNama(this.nama);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setTarifDokter(this.tarifDokter);
        user.setRole(this.role);
        user.setIsSso(false);
        return user;
    }
}
