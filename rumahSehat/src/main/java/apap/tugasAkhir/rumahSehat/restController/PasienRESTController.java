package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.*;
import apap.tugasAkhir.rumahSehat.service.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/pasien")
public class PasienRESTController {
    @Autowired
    private PasienService pasienService;

    /** Cisco:Feat14 (Melihat Profile Pasien)
     * {username} adalah username dari akun pasien
     * @return PasienModel
     */
    @GetMapping(value = "/profile")
    private PasienModel viewPasienProfile(
            @RequestHeader("Authorization") String token){
        //Gets Profile from JWT Token
        Map<String, String> decodedToken = decode(token);
        log.info("Access Profile (" + decodedToken.get("USERNAME") + ") | Token : {}",token);


        PasienModel pasienModel = pasienService.getPasienByUsername(decodedToken.get("USERNAME"));
        log.info("pasien profile: {}", pasienModel);
        return pasienModel;
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * TODO: FORM, Flutter
     * GET Function
     * {username} adalah username dari akun pasien
     */
    @GetMapping(value = "/saldo")
    private PasienModel viewPasienSaldoGet(
            @RequestHeader("Authorization") String token,
            Model model){
        //Gets Profile from JWT Token
        Map<String, String> decodedToken = decode(token);
        PasienModel pasienModel = pasienService.getPasienByUsername(decodedToken.get("USERNAME"));

        //
        model.addAttribute("saldo", pasienModel.getSaldoPasien());


        log.info("Update Saldo GET (" + decodedToken.get("USERNAME") + ")");
        return pasienModel;
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * POST Function
     * TODO: FORM, Flutter, POST
     * TODO: Action Logging (if Fail)
     */
    @PostMapping(value = "/saldo")
    private PasienModel viewPasienSaldoPost(
            @ModelAttribute PasienModel pasienModel,
            @RequestHeader("Authorization") String token,
            Model model){
        //Gets Profile from JWT Token
        Map<String, String> decodedToken = decode(token);
        PasienModel pasien = pasienService.getPasienByUsername(decodedToken.get("USERNAME"));

        //
        pasien.setSaldoPasien(pasienModel.getSaldoPasien());
        model.addAttribute("pasien", pasien);


        log.info("Update Saldo POST (" + decodedToken.get("USERNAME") + ")");
        return pasien;
    }

    /**
     * Regex Decoder Function
     * @param token jwttoken
     * @return Map<String, String> decoded token (USERNAME, EMAIL, NAMA LENGKAP)
     */
    private Map<String, String> decode(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Gson gson = new Gson();
        Map<String, String> decodedToken = gson.fromJson(payload, new TypeToken<Map<String, String>>() {}.getType());
        return decodedToken;
    }
}
