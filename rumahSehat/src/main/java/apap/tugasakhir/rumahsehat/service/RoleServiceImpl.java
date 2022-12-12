package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.RoleModel;
import apap.tugasakhir.rumahsehat.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleDb roleDb;

    @Override
    public List<RoleModel> findAll() {
        return roleDb.findAll();
    }

    @Override
    public RoleModel getById(Long id) {
        Optional<RoleModel> role = roleDb.findById(id);
        return role.orElse(null);
    }
}
