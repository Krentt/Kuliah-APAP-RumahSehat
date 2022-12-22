package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.model.RoleModel;
import apap.tugasakhir.rumahsehat.model.UserModel;
import lombok.Data;

@Data
public class ApotekerDTO {
    private String nama;
    private String username;
    private String password;
    private String email;
    private RoleModel role;

    public ApotekerDTO(String nama, String username, String password, String email, RoleModel role) {
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }


    public UserModel toUserModel(){
        var user = new ApotekerModel();
        user.setNama(this.nama);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setRole(this.role);
        user.setIsSso(false);
        return user;
    }
}
