package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.repository.DokterDb;
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
class DokterServiceImplTest {
    @MockBean
    private DokterDb dokterDb;

    @Autowired
    DokterServiceImpl dokterService;

    @Test
    void getListDokter() {
        DokterModel d1 = new DokterModel();
        DokterModel d2 = new DokterModel();
        List<DokterModel> list = new ArrayList<>();
        list.add(d1);
        list.add(d2);

        Mockito.when(dokterDb.findAll()).thenReturn(list);

        assertEquals(list, dokterService.getListDokter());
    }

    @Test
    void getDokterByUsername() {
        DokterModel dokterModel = new DokterModel();
        dokterModel.setUsername("Dokter");

        Mockito.when(dokterDb.findByUsername("Dokter")).thenReturn(dokterModel);

        assertEquals(dokterModel, dokterService.getDokterByUsername("Dokter"));
    }

    @Test
    void checkJadwal() {
        AppointmentModel appointmentModel = new AppointmentModel();
        assertEquals(false, dokterService.checkJadwal(appointmentModel));
    }
}