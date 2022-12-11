package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.TagihanModel;
import apap.tugasAkhir.rumahSehat.restModel.PaymentDTO;
import apap.tugasAkhir.rumahSehat.service.PasienService;
import apap.tugasAkhir.rumahSehat.service.TagihanService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/tagihan")
public class TagihanRESTController {
    @Autowired
    private PasienService pasienService;
    @Autowired
    private TagihanService tagihanService;


    /**
     * Cisco:Feat16 (Melihat Daftar Tagihan pasien)
     *
     * @return JSON List of TagihanModels
     */
    @GetMapping(value = "/list")
    public List<TagihanModel> viewListTagihan(
            @RequestHeader("Authorization") String token) {
        //Gets Profile from JWT Token
        Map<String, String> decodedToken = decode(token);
        PasienModel pasienModel = pasienService.getPasienByUsername(decodedToken.get("USERNAME"));


        //Gets Tagihan List for Pasien
        List<TagihanModel> tagihanModelList = new ArrayList<>(tagihanService.getListTagihanByPasien(pasienModel));


        log.info("Access List Tagihan (" + decodedToken.get("USERNAME") + ")");
        return tagihanModelList;
    }


    /**
     * Cisco:Feat17 (Melihat Detail Tagihan Pasien & Bayar Tagihan)
     */
    @PostMapping(value = "/detail")
    public Map<String, Object> bayarTagihan(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody PaymentDTO paymentDTO,
            BindingResult bindingResult) {
        //Gets Profile from JWT Token
        Map<String, String> decodedToken = decode(token);
        log.info("Update Tagihan (" + decodedToken.get("USERNAME") + ")");

        PasienModel pasienModel = pasienService.getPasienByUsername(decodedToken.get("USERNAME"));
        Map<String, Object> apiResponse = new HashMap<>();

        //Gets Specific Tagihan & Saldo pasien
        TagihanModel tagihanModel = tagihanService.getTagihanById(paymentDTO.getId());
        Long saldo = pasienModel.getSaldoPasien();

        if(bindingResult.hasFieldErrors()){
            log.info("Request body has invalid type or missing field");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            log.info("[CHECK UPDATE] Bayar Tagihan");
            boolean error = false;
            if (saldo < tagihanModel.getTotal()) {
                log.info("[ERROR] Saldo anda tidak mencukupi");
                apiResponse.put("Status", "ERROR");
                apiResponse.put("Message", "Saldo anda tidak mencukupi");
                error = true;
            }
            if (tagihanModel.getIsPaid()) {
                log.info("[ERROR] Tagihan sudah terbayar");
                apiResponse.put("Status", "ERROR");
                apiResponse.put("Message", "Tagihan sudah terbayar");
                error = true;
            }

            if (!error) {
                log.info("[UPDATE] Saldo Mencukupi, dikurangi dari Saldo");
                pasienModel.setSaldoPasien(pasienModel.getSaldoPasien() - tagihanModel.getTotal());
                pasienService.updatePasienSaldo(pasienModel);

                tagihanModel.setIsPaid(true);
                tagihanService.updateTagihan(tagihanModel);

            }
        }

        log.info("END Update Tagihan (" + decodedToken.get("USERNAME") + ")");
        return apiResponse;
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
        Map<String, String> decodedToken = gson.fromJson(payload, new TypeToken<Map<String, String>>() {
        }.getType());
        return decodedToken;
    }
}
