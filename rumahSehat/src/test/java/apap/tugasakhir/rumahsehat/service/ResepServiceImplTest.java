package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.repository.ResepDb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class ResepServiceImplTest {
    @MockBean
    private ResepDb resepDb;

    @Autowired
    ResepServiceImpl resepService;

    @Test
    void addResep() {
        ResepModel resepModel = new ResepModel();

        Mockito.when(resepDb.save(resepModel)).thenReturn(resepModel);
        assertEquals(resepModel, resepService.addResep(resepModel));
    }

    @Test
    void getListResep() {
        ResepModel r1 = new ResepModel();
        ResepModel r2 = new ResepModel();
        List<ResepModel> list = new ArrayList<>();
        list.add(r1);
        list.add(r2);

        Mockito.when(resepDb.findAll()).thenReturn(list);

        assertEquals(list, resepService.getListResep());
    }

    @Test
    void getResepByIdResep() {
        ResepModel r1 = new ResepModel();
        r1.setId(1L);
        ResepModel r2 = new ResepModel();
        r2.setId(2L);

        Mockito.when(resepDb.findById(1L)).thenReturn(java.util.Optional.of(r1));

        assertEquals(r1, resepService.getResepByIdResep(1L));
    }
}