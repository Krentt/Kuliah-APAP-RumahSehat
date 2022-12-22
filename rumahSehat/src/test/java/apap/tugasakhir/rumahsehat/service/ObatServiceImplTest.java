package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.repository.ObatDb;
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
class ObatServiceImplTest {
    @MockBean
    private ObatDb obatDb;

    @Autowired
    ObatServiceImpl obatService;

    @Test
    void getListObat() {
        // Create Obat
        ObatModel panadol = new ObatModel();
        panadol.setNamaObat("Panadol");
        panadol.setHarga(10000);

        ObatModel paracetamol = new ObatModel();
        paracetamol.setNamaObat("Paracetamol");
        paracetamol.setHarga(3300);

        List<ObatModel> list = new ArrayList<>();
        list.add(panadol);
        list.add(paracetamol);

        Mockito.when(obatDb.findAll()).thenReturn(list);

        assertEquals(list, obatService.getListObat());
    }

    @Test
    void getObatById() {
        // Create Obat
        ObatModel panadol = new ObatModel();
        panadol.setNamaObat("Panadol");
        panadol.setIdObat("P1");
        panadol.setHarga(10000);

        ObatModel paracetamol = new ObatModel();
        paracetamol.setNamaObat("Paracetamol");
        panadol.setIdObat("P2");
        paracetamol.setHarga(3300);

        Mockito.when(obatDb.findByIdObat("P1")).thenReturn(panadol);

        assertEquals(panadol, obatService.getObatById("P1"));
    }

    @Test
    void updateObat() {
        // Create Obat
        ObatModel panadol = new ObatModel();
        panadol.setNamaObat("Panadol");
        panadol.setIdObat("P1");
        panadol.setHarga(10000);

        Mockito.when(obatDb.save(panadol)).thenReturn(panadol);

        assertEquals(panadol, obatService.updateObat(panadol));
        panadol.setHarga(20000);
        assertEquals(20000, obatService.updateObat(panadol).getHarga());


    }

    @Test
    void addObat() {
        // Create Obat
        ObatModel panadol = new ObatModel();
        panadol.setNamaObat("Panadol");
        panadol.setIdObat("P1");
        panadol.setHarga(10000);

        Mockito.when(obatDb.save(panadol)).thenReturn(panadol);

        assertEquals(panadol, obatService.addObat(panadol));
    }
}