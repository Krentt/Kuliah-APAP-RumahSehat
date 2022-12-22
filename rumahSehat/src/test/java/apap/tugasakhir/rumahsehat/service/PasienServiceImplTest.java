package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.repository.PasienDb;
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
class PasienServiceImplTest {
    @MockBean
    private PasienDb pasienDb;

    @Autowired
    PasienServiceImpl pasienService;

    @Test
    void getPasienById() {
        PasienModel pasienModel = new PasienModel();
        pasienModel.setId("fc81c1a4-8231-11ed-a1eb-0242ac120002");

        //Database Function
        Mockito.when(pasienDb.findById("fc81c1a4-8231-11ed-a1eb-0242ac120002")).thenReturn(pasienModel);

        //Test
        assertEquals(pasienModel, pasienService.getPasienById("fc81c1a4-8231-11ed-a1eb-0242ac120002"));
    }

    @Test
    void getPasienByUsername() {
        PasienModel pasienModel = new PasienModel();
        pasienModel.setUsername("Username");

        //Database Function
        Mockito.when(pasienDb.findByUsername("Username")).thenReturn(pasienModel);

        //Test
        assertEquals(pasienModel, pasienService.getPasienByUsername("Username"));
    }

    @Test
    void getAllPasien() {
        PasienModel p1 = new PasienModel();
        PasienModel p2 = new PasienModel();
        List<PasienModel> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        //DB Function
        Mockito.when(pasienDb.findAll()).thenReturn(list);

        //Test
        assertEquals(list, pasienService.getAllPasien());
    }

    @Test
    void getPasienAppointment() {
        AppointmentModel appointmentModel = new AppointmentModel();
        List<AppointmentModel> list = new ArrayList<>();
        list.add(appointmentModel);

        PasienModel pasienModel = new PasienModel();
        pasienModel.setUsername("Username");
        pasienModel.setAppointmentPasien(list);

        //Database Function
        Mockito.when(pasienDb.findByUsername("Username")).thenReturn(pasienModel);

        //Test
        assertEquals(list, pasienService.getPasienAppointment("Username"));
    }

    @Test
    void updatePasienSaldo() {
        PasienModel pasienModel = new PasienModel();
        pasienModel.setUsername("Username");
        pasienModel.setSaldoPasien(100L);

        //Database Function
        Mockito.when(pasienDb.save(pasienModel)).thenReturn(pasienModel);

        //Test1
        assertEquals(100L, pasienModel.getSaldoPasien());

        pasienModel.setSaldoPasien(1000L);
        pasienModel = pasienService.updatePasienSaldo(pasienModel);

        //Test2
        assertEquals(1000L, pasienModel.getSaldoPasien());


    }
}