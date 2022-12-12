package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.repository.DokterDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokterServiceImpl implements DokterService{
    @Autowired
    DokterDb dokterDb;

    @Override
    public List<DokterModel> getListDokter() {
        return dokterDb.findAll();
    }

    @Override
    public DokterModel getDokterByUsername(String username) {
        return dokterDb.findByUsername(username);
    }

    @Override
    public boolean checkJadwal(AppointmentModel appointmentModel) {
        return false;
    }

}
