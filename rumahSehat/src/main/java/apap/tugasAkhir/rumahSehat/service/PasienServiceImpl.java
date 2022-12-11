package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.TagihanModel;
import apap.tugasAkhir.rumahSehat.model.UserModel;
import apap.tugasAkhir.rumahSehat.repository.PasienDb;
import apap.tugasAkhir.rumahSehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PasienServiceImpl implements PasienService{
    @Autowired
    private PasienDb pasienDb;


    /** Cisco:Feature 14
     * Gets pasien berdasarkan id
     * @param id search parameter pasien
     * @return pasienmodel yang memilikki id (param)
     */
    @Override
    public PasienModel getPasienById(String id) {
        return pasienDb.findById(id);
    }

    /**
     * Gets pasien by username
     * @param Username search parameter pasien
     * @return pasien model with said search param
     */
    @Override
    public PasienModel getPasienByUsername(String Username) {
        return pasienDb.findByUsername(Username);
    }

    @Override
    public List<PasienModel> getAllPasien() {
        return pasienDb.findAll();
    }

    /**
     * Gets All Appointments for specific pasien
     * @param Username milik pasien
     * @return List Appoinments milik pasien
     */
    @Override
    public List<AppointmentModel> getPasienAppointment(String Username) {
        PasienModel pasienModel = pasienDb.findByUsername(Username);

        List<AppointmentModel> appointments = new ArrayList<>(pasienModel.getAppointmentPasien());

        return appointments;
    }

    /**
     * Gets All Tagihan for specific pasien
     * Uses getPasienAppointment
     * @param Username milik pasien
     * @return List Tagihan milik Pasien
     */
    @Override
    public List<TagihanModel> getPasienTagihan(String Username) {
        List<AppointmentModel> appointments = getPasienAppointment(Username);

        List<TagihanModel> tagihan = new ArrayList<>();

//        for (AppointmentModel ap: appointments
//             ) {
//            tagihan.add(ap.getTagihanModel());
//        }

        return tagihan;
    }

    @Override
    public PasienModel updatePasienSaldo(PasienModel pasienModel) {
        return pasienDb.save(pasienModel);
    }
}
