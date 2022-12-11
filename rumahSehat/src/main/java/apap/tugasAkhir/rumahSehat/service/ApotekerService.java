package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.ApotekerModel;

import java.util.List;

public interface ApotekerService {
    List<ApotekerModel> getListApoteker();

    ApotekerModel getApotekerByUsername (String username);
}
