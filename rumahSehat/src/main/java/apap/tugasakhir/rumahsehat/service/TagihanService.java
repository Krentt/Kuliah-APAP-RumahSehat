package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;

import java.util.List;

public interface TagihanService {
    //Cisco: Feat 16
    List<TagihanModel> getListTagihanByPasien(PasienModel pasienModel);

    //Cisco: Feat 17
    TagihanModel getTagihanById(String id);

    //Cisco: Feat 17
    TagihanModel createTagihanByAppointment(AppointmentModel appointmentModel, TagihanModel tagihan);

    TagihanModel updateTagihan(TagihanModel tagihanModel);

    TagihanModel addTagihan(TagihanModel tagihan);
}
