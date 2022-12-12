package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.AdminModel;
import apap.tugasAkhir.rumahSehat.model.UserModel;
import apap.tugasAkhir.rumahSehat.security.xml.ServiceResponse;
import apap.tugasAkhir.rumahSehat.service.RoleService;
import apap.tugasAkhir.rumahSehat.service.UserService;
import apap.tugasAkhir.rumahSehat.setting.Setting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
public class HomeController {

    private WebClient webClient = WebClient.builder().build();

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    String strRedirect = "redirect:";

    @GetMapping("/")
    public String home() {
        log.info("Access Home Page");
        return "home/home";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        log.info("Access Login Page");
        return "login/login";
    }

    @GetMapping("/validate-ticket")
    public ModelAndView adminLoginSSO(
            @RequestParam(value = "ticket", required = false) String ticket,
            HttpServletRequest request, RedirectAttributes redirectAttrs
    ) {
        log.info("Validate Ticket Admin");
        var serviceResponse = this.webClient.get().uri(
                String.format(
                        Setting.SERVER_VALIDATE_TICKET,
                        ticket,
                        Setting.CLIENT_LOGIN
                )
        ).retrieve().bodyToMono(ServiceResponse.class).block();

        assert serviceResponse != null;
        var attributes = serviceResponse.getAuthenticationSuccess().getAttributes();
        String username = serviceResponse.getAuthenticationSuccess().getUser();

        // WhiteList Check
        if (!userService.whiteListCheck(username)) {
            log.info("User not in Whitelist");
            return new ModelAndView(strRedirect + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
        }

        log.info("Whitelist check clear!");

        UserModel user = userService.getUserByUsername(username);

        if (user == null) {
            user = new AdminModel();
            user.setEmail(username + "@ui.ac.id");
            user.setNama(attributes.getNama());
            user.setPassword("belajarbelajar");
            user.setUsername(username);
            user.setIsSso(true);
            user.setRole(roleService.getById(1L));
            userService.addUser(user);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "belajarbelajar");

        var securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        var httpSession = request.getSession(true);
        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        log.info("User validated");

        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/logout-sso")
    public ModelAndView logoutSSO(Principal principal) {
        log.info("Access Logout");
        UserModel user = userService.getUserByUsername(principal.getName());
        if (!user.getIsSso()) {
            log.info("User Not Admin Logout");
            return new ModelAndView("redirect:/logout");
        }

        log.info("Admin User Logout");
        return new ModelAndView(strRedirect + Setting.SERVER_LOGOUT + Setting.CLIENT_LOGOUT);
    }

    @GetMapping(value = "/login-sso")
    public ModelAndView loginSSO() {
        log.info("User login sso");
        return new ModelAndView(strRedirect + Setting.SERVER_LOGIN + Setting.CLIENT_LOGIN);
    }

}
