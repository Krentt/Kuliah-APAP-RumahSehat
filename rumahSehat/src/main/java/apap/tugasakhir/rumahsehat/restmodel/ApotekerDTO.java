package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.model.RoleModel;
import apap.tugasakhir.rumahsehat.model.UserModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApotekerDTO {
    private String nama;
    private String username;
    private String password;
    private String email;
    private RoleModel role;

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
