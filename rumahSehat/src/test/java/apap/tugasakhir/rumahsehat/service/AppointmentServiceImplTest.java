package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.repository.AppointmentDb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class AppointmentServiceImplTest {
    @MockBean
    private AppointmentDb appointmentDb;

    @Autowired
    AppointmentServiceImpl appointmentService;

    @Test
    void getListAppointment() {
        AppointmentModel a1 = new AppointmentModel();
        AppointmentModel a2 = new AppointmentModel();
        List<AppointmentModel> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);

        Mockito.when(appointmentDb.findAll()).thenReturn(list);

        assertEquals(list, appointmentService.getListAppointment());
    }

    @Test
    void createAppointent() {
        AppointmentModel a1 = new AppointmentModel();

        Mockito.when(appointmentDb.save(a1)).thenReturn(a1);

        assertEquals(a1, appointmentService.createAppointment(a1));
    }

    @Test
    void checkJadwalDokter() {
        LocalDateTime now = LocalDateTime.now();

        AppointmentModel a1 = new AppointmentModel();
        a1.setWaktuAwal(now);
        AppointmentModel a2 = new AppointmentModel();
        a2.setWaktuAwal(now.minusDays(1));
        List<AppointmentModel> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        List<AppointmentModel> list2 = new ArrayList<>();

        DokterModel dokterModel = new DokterModel();
        dokterModel.setAppointmentDokter(list);
        DokterModel dokterModel2 = new DokterModel();
        dokterModel2.setAppointmentDokter(list2);

        assertEquals(a1, appointmentService.checkJadwalDokter(now, dokterModel));
        assertEquals(null, appointmentService.checkJadwalDokter(now.plusDays(1), dokterModel));
        assertEquals(null, appointmentService.checkJadwalDokter(now, dokterModel2));
    }

    @Test
    void checkJadwalPasien() {
        LocalDateTime now = LocalDateTime.now();

        AppointmentModel a1 = new AppointmentModel();
        a1.setWaktuAwal(now);
        AppointmentModel a2 = new AppointmentModel();
        a2.setWaktuAwal(now.minusDays(1));
        List<AppointmentModel> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        List<AppointmentModel> list2 = new ArrayList<>();

        PasienModel pasienModel = new PasienModel();
        pasienModel.setAppointmentPasien(list);
        PasienModel pasienModel2 = new PasienModel();
        pasienModel2.setAppointmentPasien(list2);

        assertEquals(a1, appointmentService.checkJadwalPasien(now, pasienModel));
        assertEquals(null, appointmentService.checkJadwalPasien(now, pasienModel2));

        //Before Waktu Awal && Before Waktu Akhir
        assertEquals(null, appointmentService.checkJadwalPasien(now.minusWeeks(1), pasienModel));
        //After Waktu Awal && After Waktu Akhir
        assertEquals(null, appointmentService.checkJadwalPasien(now.plusDays(1), pasienModel));
        //After Waktu Awal && Before Waktu Akhir
        assertEquals(a1, appointmentService.checkJadwalPasien(now.plusMinutes(10), pasienModel));

        // waktuAwalInput.plusHours(1L).isBefore(waktuAkhir) == TRUE
        assertEquals(a1, appointmentService.checkJadwalPasien(now.minusMinutes(10), pasienModel));

    }

    @Test
    void getAppointmentByKode() {
        AppointmentModel a1 = new AppointmentModel();
        a1.setKode("APT-1");

        Mockito.when(appointmentDb.getAppointmentModelByKode("APT-1")).thenReturn(a1);

        assertEquals(a1, appointmentService.getAppointmentByKode("APT-1"));
    }

    @Test
    void findAllByWaktuAwalInBetween() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime last = LocalDateTime.now().minusDays(7);

        AppointmentModel a1 = new AppointmentModel();
        a1.setWaktuAwal(now);
        AppointmentModel a2 = new AppointmentModel();
        a2.setWaktuAwal(now.minusDays(1));
        AppointmentModel a3 = new AppointmentModel();
        a3.setWaktuAwal(now.plusWeeks(1));

        List<AppointmentModel> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a3);

        List<AppointmentModel> listValid = new ArrayList<>();
        list.add(a1);
        list.add(a2);

        Mockito.when(appointmentDb.findAllByWaktuAwalBetween(now,last)).thenReturn(listValid);

        assertEquals(listValid, appointmentService.findAllByWaktuAwalInBetween(now, last));
    }

    @Test
    void findAllByDokterandWaktuAwalInBetween() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime last = LocalDateTime.now().minusDays(7);

        AppointmentModel a1 = new AppointmentModel();
        a1.setWaktuAwal(now);
        AppointmentModel a2 = new AppointmentModel();
        a2.setWaktuAwal(now.minusDays(1));
        AppointmentModel a3 = new AppointmentModel();
        a3.setWaktuAwal(now.plusWeeks(1));

        List<AppointmentModel> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);
        list.add(a3);

        List<AppointmentModel> listValid = new ArrayList<>();
        list.add(a1);
        list.add(a2);

        DokterModel dokterModel = new DokterModel();
        dokterModel.setUsername("Dokter");
        dokterModel.setAppointmentDokter(list);

        Mockito.when(appointmentDb.findAllByDokterModelUsernameAndWaktuAwalBetween(dokterModel.getUsername(), now,last))
                .thenReturn(listValid);

        assertEquals(listValid, appointmentService.findAllByDokterandWaktuAwalInBetween(dokterModel, now, last));

    }
}