package apap.tugasakhir.rumahsehat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    private WebSecurityConfig(){
        throw new IllegalStateException("WebSecurityConfig");
    }


    @Configuration
    public static class UILoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {

            var roleAdmin = "Admin";
            var roleDokter = "Dokter";
            var roleApoteker = "Apoteker";

            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/login-sso","/validate-ticket").permitAll()
                    .antMatchers("/obat/view-obat").hasAnyAuthority(roleAdmin,roleApoteker)
                    .antMatchers("/obat/{idObat}/update").hasAuthority(roleApoteker)
                    .antMatchers("/user/add-dokter").hasAuthority(roleAdmin)
                    .antMatchers("/user/add-apoteker").hasAuthority(roleAdmin)
                    .antMatchers("/user/view-dokter").hasAuthority(roleAdmin)
                    .antMatchers("/user/view-apoteker").hasAuthority(roleAdmin)
                    .antMatchers("/user/view-pasien").hasAuthority(roleAdmin)
                    .antMatchers("/appointment/view-all").hasAuthority(roleAdmin)
                    .antMatchers("/chart/").hasAuthority(roleAdmin)
                    .antMatchers("/chart/bulanan-line").hasAuthority(roleAdmin)
                    .antMatchers("/chart/tahunan-line").hasAuthority(roleAdmin)
                    .antMatchers("/chart/apptLine-bulan").hasAuthority(roleAdmin)
                    .antMatchers("/chart/pptLine-tahun").hasAuthority(roleAdmin)
                    .antMatchers("/chart/quantity-bar").hasAuthority(roleAdmin)
                    .antMatchers("/chart/income-bar").hasAuthority(roleAdmin)
                    .antMatchers("/chart/appointment-bar").hasAuthority(roleAdmin)
                    .antMatchers("/appointment/dokter-view-all").hasAuthority(roleDokter)
                    .antMatchers("/appointment/{kode}").hasAnyAuthority(roleDokter, roleAdmin)
                    .antMatchers("/appointment/{kode}/selesai").hasAuthority(roleDokter)
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").permitAll()
                    .and()
                    .formLogin()
                    .defaultSuccessUrl("/", true);
        }


        private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
        }
    }
    @Configuration
    @Order(1)
    public static class RestApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

        @Autowired
        private UserDetailsService jwtUserDetailsService;

        @Autowired
        private JwtRequestFilter jwtRequestFilter;

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            // configure AuthenticationManager so that it knows from where to load
            // user for matching credentials
            // Use BCryptPasswordEncoder
            auth.userDetailsService(jwtUserDetailsService).passwordEncoder(encoder);
        }

        private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        // Untuk API
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            // We don't need CSRF for this example
            httpSecurity.csrf().disable()
                    .requestMatchers(matchers -> matchers
                            .antMatchers("/authenticate")
                            .antMatchers("/signup")
                            .antMatchers("/jwt-valid")
                            .antMatchers("/error")
                            .antMatchers("/tagihan/**")
                            .antMatchers("/pasien/**")
                            .antMatchers("/appointment/add")
                            .antMatchers("/appointment/pasien-view-all")
                            .antMatchers("/dokter/get-all")
                            .antMatchers("/resep/view/{kode}"))
                    // dont authenticate this particular request
                    .authorizeRequests().antMatchers("/authenticate").permitAll()
                    .antMatchers("/signup").permitAll()
                    .antMatchers("/error").permitAll()
                    // all other requests need to be authenticated
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                            .and()
                                    .sessionManagement()
                                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // Add a filter to validate the tokens with every request
            httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
}
