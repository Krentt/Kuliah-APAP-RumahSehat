package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.PasienModel;

public interface TagihanService {
    //Cisco: Feat15-16
    PasienModel getTagihanbyResepId(String id);
}
