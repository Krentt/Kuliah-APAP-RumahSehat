package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.*;
import apap.tugasakhir.rumahsehat.repository.TagihanDb;
import apap.tugasakhir.rumahsehat.restmodel.ResepModelDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
class TagihanServiceImplTest {
    @Autowired
    TagihanServiceImpl tagihanService;

    @MockBean
    private TagihanDb tagihanDb;

    @Test
    void getListTagihanByPasien() {
        //Create Appointment
        List<AppointmentModel> appointmentModelList = new ArrayList<>();
        AppointmentModel appointmentModel = createAppointmentModel();
        appointmentModelList.add(createAppointmentModel());

        //Create Tagihan
        TagihanModel tagihan1 = new TagihanModel();
        tagihan1.setId("BILL-1");
        tagihan1.setAppointmentModel(appointmentModel);

        List<TagihanModel> tagihanModelList = new ArrayList<>();
        tagihanModelList.add(tagihan1);


        PasienModel pasienModel = new PasienModel();
        pasienModel.setAppointmentPasien(appointmentModelList);

        //Database Function
        Mockito.when(tagihanDb.findTagihanModelByAppointmentModelPasienModel(pasienModel)).thenReturn(tagihanModelList);

        assertEquals(tagihanModelList,tagihanService.getListTagihanByPasien(pasienModel));
    }

    @Test
    void getTagihanById() {
        //Create Tagihan
        TagihanModel tagihan1 = new TagihanModel();
        tagihan1.setId("BILL-1");
        TagihanModel tagihan2 = new TagihanModel();
        tagihan1.setId("BILL-2");
        TagihanModel tagihan3 = new TagihanModel();
        tagihan1.setId("BILL-3");


        //Database Function
        Mockito.when(tagihanDb.findById("BILL-1")).thenReturn(tagihan1);
        Mockito.when(tagihanDb.findById("BILL-2")).thenReturn(tagihan2);
        Mockito.when(tagihanDb.findById("BILL-3")).thenReturn(tagihan3);


        //Test
        assertEquals(tagihan1, tagihanService.getTagihanById("BILL-1"));
        assertEquals(tagihan2, tagihanService.getTagihanById("BILL-2"));
    }

    @Test
    void updateTagihan() {
        //Create Tagihan
        TagihanModel tagihan = new TagihanModel();

        Mockito.when(tagihanDb.save(tagihan)).thenReturn(tagihan);

        assertEquals(tagihan, tagihanService.updateTagihan(tagihan));

        tagihan.setIsPaid(true);
        assertEquals(tagihan, tagihanService.updateTagihan(tagihan));
    }

    @Test
    void addTagihan() {
        //Create Tagihan
        TagihanModel tagihan = new TagihanModel();

        Mockito.when(tagihanDb.save(tagihan)).thenReturn(tagihan);

        assertEquals(tagihan, tagihanService.addTagihan(tagihan));
    }

    @Test
    void createTagihanByAppointment() {
        AppointmentModel appointmentModel = createAppointmentModel();

        TagihanModel tagihanModel = new TagihanModel();
        Mockito.when(tagihanDb.save(tagihanModel)).thenReturn(tagihanModel);

        assertEquals(tagihanModel, tagihanService.createTagihanByAppointment(appointmentModel, tagihanModel));
    }

    private AppointmentModel createAppointmentModel(){
        AppointmentModel appointment = new AppointmentModel();

        // Create Obat
        ObatModel panadol = new ObatModel();
        panadol.setNamaObat("Panadol");
        panadol.setHarga(10000);

        ObatModel paracetamol = new ObatModel();
        paracetamol.setNamaObat("Paracetamol");
        paracetamol.setHarga(3300);

        //Create Jumlah & list Jumlah
        JumlahModel jumlahpanadol = new JumlahModel();
        jumlahpanadol.setObat(panadol);
        jumlahpanadol.setKuantitas(3);

        JumlahModel jumlahparacetamol = new JumlahModel();
        jumlahparacetamol.setObat(paracetamol);
        jumlahparacetamol.setKuantitas(3);

        List<JumlahModel> listJumlah = new ArrayList<>();
        listJumlah.add(jumlahpanadol);
        listJumlah.add(jumlahparacetamol);

        // Create Resep
        ResepModelDTO resepdto = new ResepModelDTO(1L,true, LocalDateTime.now().minusDays(1),
                listJumlah, appointment, null);
        ResepModel resep = resepdto.toModel();

        // Create Dokter
        DokterModel dokterModel = new DokterModel();
        dokterModel.setTarifDokter(111);

        // Create Appointment
        appointment.setKode("APT-1");
        appointment.setResepModel(resep);
        appointment.setWaktuAwal(LocalDateTime.now());
        appointment.setDokterModel(dokterModel);

        return appointment;
    }



}