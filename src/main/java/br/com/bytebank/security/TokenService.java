package br.com.bytebank.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    //Fabrica o token
    public String gerarToken(Usuario usuario) {
        try{

            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("API Bytebank") //Emissor do token.
                    .withSubject(usuario.getLogin())//Quem recebe
                    .withExpiresAt(dataExpiracao())//quando vence
                    .sign(algoritmo);
        }catch(JWTCreationException exception){
            throw new RuntimeException("erro ao gerar Token. ", exception);
        }


    }

    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }










}
