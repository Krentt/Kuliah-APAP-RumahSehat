package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.UserModel;
import apap.tugasakhir.rumahsehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

    @Override
    public void addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    // Username dari SSO tidak mungkin sama sehingga pemeriksaan menggunakan username
    @Override
    public boolean whiteListCheck(String username) {
        List<String> whiteList = new ArrayList<>();
        Collections.addAll(whiteList, "marvel.krent", "shabiqa.amani", "cisco.salya",
                "siti.fatimah05", "syauqi.muhammad");
        return whiteList.contains(username);
    }
}
