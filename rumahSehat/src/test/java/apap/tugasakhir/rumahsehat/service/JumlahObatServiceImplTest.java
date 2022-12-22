package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.JumlahModel;
import apap.tugasakhir.rumahsehat.repository.JumlahObatDb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class JumlahObatServiceImplTest {
    @MockBean
    private JumlahObatDb jumlahObatDb;

    @Autowired
    JumlahObatServiceImpl jumlahObatService;

    @Test
    void getListJumlahObat() {
        JumlahModel j1 = new JumlahModel();
        JumlahModel j2 = new JumlahModel();
        List<JumlahModel> list = new ArrayList<>();
        list.add(j1);
        list.add(j2);

        Mockito.when(jumlahObatDb.findAll()).thenReturn(list);

        assertEquals(list, jumlahObatService.getListJumlahObat());
    }
}