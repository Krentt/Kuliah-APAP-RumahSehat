package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.repository.AppointmentDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    AppointmentDb appointmentDb;

    @Override
    public List<AppointmentModel> getListAppointment() {
        return appointmentDb.findAll();
    }
}
