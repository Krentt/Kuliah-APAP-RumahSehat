package apap.tugasakhir.rumahsehat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nama")
    private String nama;

    @NotNull
    @Size(max = 50)
    @Column(name="username", unique = true)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password")
    private String password;

    @NotNull
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "is_Sso", nullable = false)
    private Boolean isSso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", nama='" + nama + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isSso=" + isSso +
                ", role=" + role +
                '}';
    }
}
