package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.repository.AppointmentDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService{
    @Autowired
    AppointmentDb appointmentDb;

    public int[] getDataPendapatan(List<AppointmentModel> listAppointment) {
        var data = new int[12];

        for (AppointmentModel appointment : listAppointment) {
            data[appointment.getWaktuAwal().getMonthValue()-1] += appointment.getDokterModel().getTarifDokter();
        }

        return data;
    }

    @Override
    public List<int[]> getDataLineTahun(int year, List<DokterModel> dokterModelList) {
        var awal = LocalDateTime.of(year, 1, 1, 0, 0);
        LocalDateTime akhir = awal.plusYears(1).minusMinutes(1);

        List<int[]> hasil = new ArrayList<>();

        for (DokterModel dokterModel : dokterModelList) {
            var data = new int[12];
            List<AppointmentModel> appointmentModelList = appointmentDb
                    .findAllByDokterModel_UsernameAndWaktuAwalBetween(
                            dokterModel.getUsername(),
                            awal,
                            akhir
                    );
            for (AppointmentModel appointmentModel : appointmentModelList) {
                data[appointmentModel.getWaktuAwal().getMonthValue()-1] += dokterModel.getTarifDokter();
            }
            hasil.add(data);
        }

        return hasil;
    }

    @Override
    public List<int[]> getDataLineBulan(int year, int month, List<DokterModel> dokterModelList) {
        var awal = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime akhir = awal.plusMonths(1).minusMinutes(1);

        List<int[]> hasil = new ArrayList<>();

        for (DokterModel dokterModel : dokterModelList) {
            var data = new int[akhir.getDayOfMonth()];
            List<AppointmentModel> appointmentModelList = appointmentDb
                    .findAllByDokterModel_UsernameAndWaktuAwalBetween(
                            dokterModel.getUsername(),
                            awal,
                            akhir
                    );
            for (AppointmentModel appointmentModel : appointmentModelList) {
                data[appointmentModel.getWaktuAwal().getDayOfMonth()-1] += dokterModel.getTarifDokter();
            }
            hasil.add(data);
        }
        return hasil;
    }
}
