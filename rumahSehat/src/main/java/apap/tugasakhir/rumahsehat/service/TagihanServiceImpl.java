package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;
import apap.tugasakhir.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagihanServiceImpl implements TagihanService{
    @Autowired
    private TagihanDb tagihanDb;

    @Override
    public List<TagihanModel> getListTagihanByPasien(PasienModel pasienModel) {
        return tagihanDb.findTagihanModelByAppointmentModelPasienModel(pasienModel);
    }

    @Override
    public TagihanModel getTagihanById(String id) {
        return tagihanDb.findById(id);
    }

    @Override
    public TagihanModel createTagihanByAppointment(AppointmentModel appointmentModel) {
        var tagihan = new TagihanModel();
        tagihan.setTanggalTerbuat(appointmentModel.getWaktuAwal());
        tagihan.setTanggalBayar(appointmentModel.getWaktuAwal().plusWeeks(2));
        tagihan.setIsPaid(false);
        tagihan.setAppointmentModel(appointmentModel);
        tagihan.calculateTotal();

        return tagihanDb.save(tagihan);
    }

    @Override
    public TagihanModel updateTagihan(TagihanModel tagihanModel) {
        return tagihanDb.save(tagihanModel);
    }

    @Override
    public void addTagihan(TagihanModel tagihan){
        tagihanDb.save(tagihan);
    }
}
