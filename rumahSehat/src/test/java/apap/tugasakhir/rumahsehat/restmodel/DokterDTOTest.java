package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.RoleModel;
import apap.tugasakhir.rumahsehat.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class DokterDTOTest {

    private DokterDTO createDTO(){
        DokterDTO dokterDTO = new DokterDTO(
                "Test",
                "Test",
                "password",
                "email",
                100,
                null
        );

        return dokterDTO;
    }

    @Test
    void constructor() {
        RoleModel dokter = new RoleModel();
        dokter.setRole("dokter");

        DokterDTO dokterDTO = createDTO();
        dokterDTO.setRole(dokter);

        assertEquals("Test", dokterDTO.getNama());
        assertEquals("Test", dokterDTO.getUsername());
        assertEquals("password", dokterDTO.getPassword());
        assertEquals("email", dokterDTO.getEmail());
        assertEquals(100, dokterDTO.getTarifDokter());
        assertEquals(dokter, dokterDTO.getRole());
    }

    @Test
    void toUserModel() {
        RoleModel dokter = new RoleModel();
        dokter.setRole("dokter");

        DokterDTO dokterDTO = createDTO();
        dokterDTO.setRole(dokter);

        assertEquals(DokterModel.class, dokterDTO.toUserModel().getClass());
    }
}