package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.*;
import apap.tugasAkhir.rumahSehat.service.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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
     * @return JSON List of TagihanModels
     */
    @GetMapping(value = "/list")
    private List<TagihanModel> viewListTagihan(
            @RequestHeader("Authorization") String token){
        //Gets Profile from JWT Token
        Map<String, String> decodedToken = decode(token);
        PasienModel pasienModel = pasienService.getPasienByUsername(decodedToken.get("USERNAME"));


        //Gets Tagihan List for Pasien
        List<TagihanModel> tagihanModelList = new ArrayList<>(tagihanService.getListTagihanByPasien(pasienModel));


        log.info("Access List Tagihan (" + decodedToken.get("USERNAME") + ")");
        return tagihanModelList;
    }


    /**
     * Cisco:Feat17 (Melihat Detail Tagihan Pasien)
     * TODO: Add function membayar (post)
     * TODO: To Not use ID Path Variable
     * {id} adalah ID dari tagihan (BILL-x)
     */
    @GetMapping(value = "/{id}/detail")
    private TagihanModel viewTagihanDetail(
            @PathVariable String id,
            @RequestHeader("Authorization") String token){
        //Gets Profile from JWT Token
        Map<String, String> decodedToken = decode(token);
        PasienModel pasienModel = pasienService.getPasienByUsername(decodedToken.get("USERNAME"));

        //Gets Specific Tagihan & Saldo pasien
        TagihanModel tagihanModel = tagihanService.getTagihanById(id);
        Long saldo = pasienModel.getSaldoPasien();



        // TODO: Add Saldo to JSON


        log.info("Access List Tagihan (" + decodedToken.get("USERNAME") + ")");
        return tagihanModel;
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
