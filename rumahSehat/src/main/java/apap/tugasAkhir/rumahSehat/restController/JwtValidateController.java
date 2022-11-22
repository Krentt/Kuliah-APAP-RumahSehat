package apap.tugasAkhir.rumahSehat.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtValidateController {
    @GetMapping(value = "/jwt-valid")
    public String jwtValid(){
        return "Login Success!";
    }
}
