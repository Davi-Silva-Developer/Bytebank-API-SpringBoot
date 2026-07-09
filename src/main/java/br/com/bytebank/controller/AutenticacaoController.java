package br.com.bytebank.controller;


import br.com.bytebank.dto.DadosAutenticacao;
import br.com.bytebank.dto.DadosTokenJWT;
import br.com.bytebank.model.Cliente;
import br.com.bytebank.security.TokenService;
import br.com.bytebank.security.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            // Cria o token de autenticação com login e senha
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());

            // Autentica o usuário (Spring Security verifica no banco e compara senhas)
            Authentication authentication = manager.authenticate(authenticationToken);

            // Gera o JWT usando o usuário autenticado
            String tokenJWT = tokenService.gerarToken((Users) authentication.getPrincipal());

            // Retorna o token no corpo da resposta
            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

        } catch (Exception e) {
            // Retorna erro 401 caso login/senha estejam incorretos
            return ResponseEntity.status(401).build();
        }
    }
}
