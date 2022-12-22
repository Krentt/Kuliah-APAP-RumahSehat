package apap.tugasakhir.rumahsehat.restmodel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class AppointmentDTOTest {
    @Test
    void setterTest() throws NoSuchFieldException, IllegalAccessException {
        //given
        final AppointmentDTO object = new AppointmentDTO();
        LocalDateTime now = LocalDateTime.now();

        //when
        object.setKode("APT-1");
        object.setWaktuAwal(now);
        object.setDone(false);
        object.setKodeResep("Resep");
        object.setDokterName("Dokter");
        object.setPasienName("Pasien");

        //then
        final Field kode = object.getClass().getDeclaredField("kode");
        kode.setAccessible(true);
        assertEquals("APT-1", kode.get(object), "Fields didn't match");

        final Field waktuAwal = object.getClass().getDeclaredField("waktuAwal");
        waktuAwal.setAccessible(true);
        assertEquals(now, waktuAwal.get(object), "Fields didn't match");

        final Field isDone = object.getClass().getDeclaredField("isDone");
        isDone.setAccessible(true);
        assertEquals(false, isDone.get(object),"Fields didn't match" );

        final Field KodeResep = object.getClass().getDeclaredField("kodeResep");
        KodeResep.setAccessible(true);
        assertEquals("Resep", KodeResep.get(object),"Fields didn't match");

        final Field DokterName = object.getClass().getDeclaredField("dokterName");
        DokterName.setAccessible(true);
        assertEquals("Dokter", DokterName.get(object), "Fields didn't match");

        final Field PasienName = object.getClass().getDeclaredField("pasienName");
        PasienName.setAccessible(true);
        assertEquals("Pasien", PasienName.get(object), "Fields didn't match");

    }
}