package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.RoleModel;
import apap.tugasAkhir.rumahSehat.model.TagihanModel;
import apap.tugasAkhir.rumahSehat.repository.RoleDb;
import apap.tugasAkhir.rumahSehat.repository.TagihanDb;
import apap.tugasAkhir.rumahSehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TagihanServiceImpl implements TagihanService{
    @Autowired
    private TagihanDb tagihanDb;

    @Override
    public List<TagihanModel> getListTagihanByPasien(PasienModel pasienModel) {
        return tagihanDb.findTagihanModelByAppointmentModel_PasienModel(pasienModel);
    }

    @Override
    public TagihanModel getTagihanById(String id) {
        return tagihanDb.findById(id);
    }

    @Override
    public TagihanModel createTagihanByAppointment(AppointmentModel appointmentModel) {
        TagihanModel tagihan = new TagihanModel();
        tagihan.setTanggalTerbuat(appointmentModel.getWaktuAwal());
        tagihan.setTanggalBayar(appointmentModel.getWaktuAwal().plusWeeks(2));
        tagihan.setIsPaid(false);
        tagihan.setAppointmentModel(appointmentModel);
        tagihan.calculateTotal();

        return tagihanDb.save(tagihan);
    }
}
