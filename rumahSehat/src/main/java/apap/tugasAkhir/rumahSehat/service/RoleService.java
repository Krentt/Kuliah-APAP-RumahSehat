package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> findAll();
    RoleModel getById(Long id);
}
