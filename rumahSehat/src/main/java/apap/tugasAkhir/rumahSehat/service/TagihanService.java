package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.TagihanModel;

import java.util.List;

public interface TagihanService {
    //Cisco: Feat 16
    List<TagihanModel> getListTagihanByPasien(PasienModel pasienModel);

    //Cisco: Feat 17
    TagihanModel getTagihanById(String id);

    //Cisco: Feat 17
    TagihanModel createTagihanByAppointment(AppointmentModel appointmentModel);

    TagihanModel updateTagihan(TagihanModel tagihanModel);

    void addTagihan(TagihanModel tagihan);
}
