package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.RoleModel;
import apap.tugasakhir.rumahsehat.model.UserModel;
import apap.tugasakhir.rumahsehat.repository.UserDb;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @MockBean
    private UserDb userDb;

    @Autowired
    UserServiceImpl userService;

    @Test
    void addUser() {
        UserModel user = new UserModel();
        user.setPassword("Password");

        Mockito.when(userDb.save(user)).thenReturn(user);

        UserModel found = userService.addUser(user);

        assertEquals(user, found);
        assertNotEquals("Password", found.getPassword());
    }

    @Test
    void encrypt() {
        assertNotEquals("Password", userService.encrypt("Password"));
    }

    @Test
    void getUserByUsername() {
        //Create User Model
        UserModel userModel = new UserModel();
        userModel.setId("efe57c8e-8221-11ed-a1eb-0242ac120002");
        userModel.setNama("Test Model");
        userModel.setUsername("Test Username");
        userModel.setPassword("Password");
        userModel.setEmail("Test@Email.com");
        userModel.setIsSso(false);

        //Database Function
        Mockito.when(userDb.findByUsername("Test Username")).thenReturn(userModel);

        //Test
        assertEquals(userModel, userService.getUserByUsername("Test Username"));
    }

    @Test
    void whiteListCheck() {
        assertEquals(true, userService.whiteListCheck("cisco.salya"));
        assertEquals(false, userService.whiteListCheck("not whitelist"));
    }
}