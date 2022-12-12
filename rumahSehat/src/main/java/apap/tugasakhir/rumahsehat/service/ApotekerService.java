package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;

import java.util.List;

public interface ApotekerService {
    List<ApotekerModel> getListApoteker();

    ApotekerModel getApotekerByUsername (String username);
}
