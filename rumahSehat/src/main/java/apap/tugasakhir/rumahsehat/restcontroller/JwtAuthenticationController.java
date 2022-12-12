package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.restmodel.JwtRequest;
import apap.tugasakhir.rumahsehat.restmodel.JwtResponse;
import apap.tugasakhir.rumahsehat.restmodel.PasienDTO;
import apap.tugasakhir.rumahsehat.security.JwtTokenUtil;
import apap.tugasakhir.rumahsehat.service.RoleService;
import apap.tugasakhir.rumahsehat.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Slf4j
@Controller
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;


    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        log.info("User login API");
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final var userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.info("User Disabled");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.info("Invalid Credentials!");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    // Rest Controller PASIEN SIGN UP
    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerPasien(@RequestBody PasienDTO pasienDTO){
        log.info("User Signup API");
        // Check username
        if (userService.getUserByUsername(pasienDTO.getUsername()) != null){
            log.info("Username is already taken!");
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        var pasienModel = new PasienModel();
        pasienModel.setNama(pasienDTO.getNama());
        pasienModel.setUsername(pasienDTO.getUsername());
        pasienModel.setPassword(pasienDTO.getPassword());
        pasienModel.setEmail(pasienDTO.getEmail());
        pasienModel.setSaldoPasien(0L);
        pasienModel.setUmurPasien(pasienDTO.getUmurPasien());
        pasienModel.setIsSso(false);
        pasienModel.setRole(roleService.getById(2L));
        userService.addUser(pasienModel);

        final var userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(pasienModel.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
