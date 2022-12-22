package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.model.RoleModel;
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
class ApotekerDTOTest {

    @Test
    void constructor() {
        RoleModel apoteker = new RoleModel();
        apoteker.setRole("apoteker");

        ApotekerDTO apotekerDTO = new ApotekerDTO(
                "Test",
                "Test",
                "password",
                "email",
                apoteker
        );

        assertEquals("Test", apotekerDTO.getNama());
        assertEquals("Test", apotekerDTO.getUsername());
        assertEquals("password", apotekerDTO.getPassword());
        assertEquals("email", apotekerDTO.getEmail());
        assertEquals(apoteker, apotekerDTO.getRole());
    }

    @Test
    void toUserModel() {
        RoleModel apoteker = new RoleModel();
        apoteker.setRole("apoteker");

        ApotekerDTO apotekerDTO = new ApotekerDTO(
                "Test",
                "Test",
                "password",
                "email",
                apoteker
        );
        assertEquals(ApotekerModel.class, apotekerDTO.toUserModel().getClass());

    }
}