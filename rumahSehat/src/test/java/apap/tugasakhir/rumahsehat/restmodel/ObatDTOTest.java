package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.JumlahModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.model.RoleModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class ObatDTOTest {

    @Test
    void constructor() {
        List<JumlahModel> jumlahModelList = new ArrayList<>();

        ObatDTO obatDTO = new ObatDTO(
                "Test",
                "Test",
                1,
                100,
                jumlahModelList
        );

        assertEquals("Test", obatDTO.getIdObat());
        assertEquals("Test", obatDTO.getNamaObat());
        assertEquals(1, obatDTO.getStok());
        assertEquals(100, obatDTO.getHarga());
        assertEquals(jumlahModelList, obatDTO.getListJumlahModel());
    }

    @Test
    void toModel() {
        ObatDTO obatDTO = new ObatDTO(
                "Test",
                "Test",
                1,
                100,
                null
        );

        assertEquals(ObatModel.class, obatDTO.toModel().getClass());
    }
}