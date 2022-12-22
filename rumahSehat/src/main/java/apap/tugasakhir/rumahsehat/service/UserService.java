package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    UserModel getUserByUsername(String username);

    boolean whiteListCheck(String username);
}
