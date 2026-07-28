package br.com.bytebank.controller;


import br.com.bytebank.dto.DadosAutenticacao;
import br.com.bytebank.dto.DadosTokenJWT;
import br.com.bytebank.service.TokenService;
import br.com.bytebank.model.Users;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/login", "/api/login"})
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager manager;

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados, HttpServletResponse response) {
        try {
            // Cria o token de autenticação com account e password
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dados.account(), dados.password());

            // Autentica o usuário (verifica no banco e compara senhas)
            Authentication authentication = manager.authenticate(authenticationToken);

            // Gera o JWT usando o usuário autenticado
            String tokenJWT = tokenService.gerarToken((Users) authentication.getPrincipal());






            // Cria o cookie HttpOnly
            Cookie cookie = new Cookie("token", tokenJWT);
            cookie.setHttpOnly(true);
            cookie.setSecure(false); // Em produção deve ser true (HTTPS)
            cookie.setPath("/");
            cookie.setMaxAge(86400); // 1 dia

            // Adiciona o cookie na resposta
            response.addCookie(cookie);

            // Retorna o token no corpo da resposta pra compatibilidade com o front
            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

        } catch (Exception e) {
            // Retorna erro 401 caso login/senha estejam incorretos
            return ResponseEntity.status(401).build();
        }
    }
}
