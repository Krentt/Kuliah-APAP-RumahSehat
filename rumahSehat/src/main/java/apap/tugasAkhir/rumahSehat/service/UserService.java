package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.UserModel;

public interface UserService {
    void addUser(UserModel user);
    String encrypt(String password);
    UserModel getUserByUsername(String username);

    boolean whiteListCheck(String username);
}
