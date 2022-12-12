package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.restmodel.SaldoDTO;
import apap.tugasakhir.rumahsehat.service.PasienService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/pasien")
public class PasienRESTController {
    @Autowired
    private PasienService pasienService;

    String strusername = "USERNAME";

    /** Cisco:Feat14 (Melihat Profile Pasien)
     * {username} adalah username dari akun pasien
     * @return PasienModel
     */
    @GetMapping(value = "/profile")
    public PasienModel viewPasienProfile(
            @RequestHeader("Authorization") String token){
        //Gets Profile from JWT Token
        Map<String, String> decodedToken = decode(token);
        log.info("Access Profile (" + decodedToken.get(strusername) + ") | Token : {}",token);


        var pasienModel = pasienService.getPasienByUsername(decodedToken.get(strusername));
        log.info("pasien profile: {}", pasienModel);
        return pasienModel;
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * GET Function
     * {username} adalah username dari akun pasien
     */
    @GetMapping(value = "/saldo")
    public PasienModel viewPasienSaldoGet(
            @RequestHeader("Authorization") String token,
            Model model){
        //Gets Profile from JWT Token
        Map<String, String> decodedToken = decode(token);
        var pasienModel = pasienService.getPasienByUsername(decodedToken.get(strusername));

        //
        model.addAttribute("saldo", pasienModel.getSaldoPasien());


        log.info("Update Saldo GET (" + decodedToken.get(strusername) + ")");
        return pasienModel;
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * POST Function
     */
    @PostMapping(value = "/saldo")
    public Map<String, Object> viewPasienSaldoPost(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody SaldoDTO saldoDTO,
            BindingResult bindingResult){
        Map<String, String> decodedToken = decode(token);
        PasienModel newPasien;
        Map<String, Object> apiResp = new HashMap<>();

        if(bindingResult.hasFieldErrors()){
            log.info("Request body has invalid type or missing field");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            log.info("Access Update Saldo API");
            if (saldoDTO.getSaldo() < 0){
                log.info("[GAGAL UPDATE] Saldo Tidak Valid");

            } else {
                log.info("[UPDATE] Saldo Valid");
                PasienModel pasien = pasienService.getPasienByUsername(decodedToken.get(strusername));
                pasien.setSaldoPasien(pasien.getSaldoPasien() + saldoDTO.getSaldo());


                newPasien = pasienService.updatePasienSaldo(pasien);
                apiResp.put("body", newPasien);

            }
        }


        log.info("Update Saldo POST (" + decodedToken.get(strusername) + ")");
        return apiResp;
    }

    /**
     * Regex Decoder Function
     * @param token jwttoken
     * @return Map<String, String> decoded token (USERNAME, EMAIL, NAMA LENGKAP)
     */
    private Map<String, String> decode(String token) {
        String[] chunks = token.split("\\.");
        var decoder = Base64.getUrlDecoder();
        var payload = new String(decoder.decode(chunks[1]));
        var gson = new Gson();
        return gson.fromJson(payload, new TypeToken<Map<String, String>>() {}.getType());
    }
}
