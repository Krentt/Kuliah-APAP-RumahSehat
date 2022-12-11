package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.TagihanModel;
import apap.tugasAkhir.rumahSehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * @param username search parameter pasien
     * @return pasien model with said search param
     */
    @Override
    public PasienModel getPasienByUsername(String username) {
        return pasienDb.findByUsername(username);
    }

    @Override
    public List<PasienModel> getAllPasien() {
        return pasienDb.findAll();
    }

    /**
     * Gets All Appointments for specific pasien
     * @param username milik pasien
     * @return List Appoinments milik pasien
     */
    @Override
    public List<AppointmentModel> getPasienAppointment(String username) {
        PasienModel pasienModel = pasienDb.findByUsername(username);

        return pasienModel.getAppointmentPasien();
    }


    @Override
    public PasienModel updatePasienSaldo(PasienModel pasienModel) {
        return pasienDb.save(pasienModel);
    }
}
