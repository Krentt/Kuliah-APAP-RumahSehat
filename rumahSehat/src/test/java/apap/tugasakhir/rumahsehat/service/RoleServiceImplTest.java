package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.RoleModel;
import apap.tugasakhir.rumahsehat.repository.RoleDb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class RoleServiceImplTest {
    @MockBean
    private RoleDb roleDb;

    @Autowired
    RoleServiceImpl roleService;

    @Test
    void findAll() {
        List<RoleModel> list = new ArrayList<>();
        RoleModel role1 = new RoleModel();
        role1.setId(1L);
        role1.setRole("role1");
        RoleModel role2 = new RoleModel();
        role1.setId(2L);
        role1.setRole("role2");
        list.add(role1);
        list.add(role2);

        //Database Function
        Mockito.when(roleDb.findAll()).thenReturn(list);

        //Test
        assertEquals(list, roleService.findAll());
    }

    @Test
    void getById() {
        List<RoleModel> list = new ArrayList<>();
        RoleModel role1 = new RoleModel();
        role1.setId(1L);
        role1.setRole("role1");
        RoleModel role2 = new RoleModel();
        role1.setId(2L);
        role1.setRole("role2");
        list.add(role1);
        list.add(role2);


        //Database Function
        Mockito.when(roleDb.findById(1L)).thenReturn(java.util.Optional.of(role1));
        Mockito.when(roleDb.findById(2L)).thenReturn(java.util.Optional.of(role2));

        assertEquals(role1, roleService.getById(1L));
        assertEquals(role2, roleService.getById(2L));

    }
}