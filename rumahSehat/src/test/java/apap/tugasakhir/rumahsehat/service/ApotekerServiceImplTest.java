package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.repository.ApotekerDb;
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
class ApotekerServiceImplTest {
    @MockBean
    private ApotekerDb apotekerDb;

    @Autowired
    ApotekerServiceImpl apotekerService;

    @Test
    void getListApoteker() {
        ApotekerModel a1 = new ApotekerModel();
        ApotekerModel a2 = new ApotekerModel();
        List<ApotekerModel> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);

        Mockito.when(apotekerDb.findAll()).thenReturn(list);

        assertEquals(list, apotekerService.getListApoteker());
    }

    @Test
    void getApotekerByUsername() {
        ApotekerModel a1 = new ApotekerModel();
        a1.setUsername("a1");
        ApotekerModel a2 = new ApotekerModel();
        a2.setUsername("a2");
        List<ApotekerModel> list = new ArrayList<>();
        list.add(a1);
        list.add(a2);

        Mockito.when(apotekerDb.findByUsername("a1")).thenReturn(java.util.Optional.of(a1));

        assertEquals(a1, apotekerService.getApotekerByUsername("a1"));
    }
}