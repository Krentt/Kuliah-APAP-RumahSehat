package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> findAll();
    RoleModel getById(Long id);
}
