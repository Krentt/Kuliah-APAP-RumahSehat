package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.restModel.PasienDTO;
import apap.tugasAkhir.rumahSehat.security.JwtTokenUtil;
import apap.tugasAkhir.rumahSehat.restModel.JwtRequest;
import apap.tugasAkhir.rumahSehat.restModel.JwtResponse;
import apap.tugasAkhir.rumahSehat.service.RoleService;
import apap.tugasAkhir.rumahSehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

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


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
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
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    // Rest Controller PASIEN SIGN UP
    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerPasien(@RequestBody PasienDTO pasienDTO){
        // Check username
        if (userService.getUserByUsername(pasienDTO.getUsername()) != null){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        PasienModel pasienModel = new PasienModel();
        pasienModel.setNama(pasienDTO.getNama());
        pasienModel.setUsername(pasienDTO.getUsername());
        pasienModel.setPassword(pasienDTO.getPassword());
        pasienModel.setEmail(pasienDTO.getEmail());
        pasienModel.setSaldoPasien(0L);
        pasienModel.setUmurPasien(pasienDTO.getUmurPasien());
        pasienModel.setIsSso(false);
        pasienModel.setRole(roleService.getById(Long.valueOf(2)));
        userService.addUser(pasienModel);

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(pasienModel.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
//        return new ResponseEntity<>("User registered succesfully", HttpStatus.OK);
    }
}
