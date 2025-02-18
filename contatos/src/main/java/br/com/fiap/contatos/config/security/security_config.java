package br.com.fiap.contatos.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.BindParam;


@Configuration
@EnableWebSecurity
public class security_config {

    //FilterChain -- Sequencia de filtros de segurança no sistema


    //metodo para controle das requisições http
    @Bean
    public SecurityFilterChain filtrar_cadeia_deSeguranca(
            HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/registro").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/contatos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/contatos").hasRole("admin")
                        .anyRequest()
                        .authenticated())
                .build();

    }


    //Metodo Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }


    //Metodo para encode/criptografia da senha do usuario
    @Bean
    public PasswordEncoder password_Encoder(){
        return new BCryptPasswordEncoder();
    }

}
