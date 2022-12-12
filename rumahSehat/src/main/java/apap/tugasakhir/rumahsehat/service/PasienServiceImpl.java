package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class PasienServiceImpl implements PasienService, Serializable {
    @Autowired
    private transient PasienDb pasienDb;


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
        var pasienModel = pasienDb.findByUsername(username);

        return pasienModel.getAppointmentPasien();
    }


    @Override
    public PasienModel updatePasienSaldo(PasienModel pasienModel) {
        return pasienDb.save(pasienModel);
    }
}
