package br.com.bytebank.service;


import br.com.bytebank.model.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
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
    public String gerarToken(Users users) {
        try{

            Algorithm algoritmo = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("API Bytebank") //Emissor do token.
                    .withSubject(users.getLogin())//Quem recebe o token
                    .withExpiresAt(dataExpiracao())//quando vence.
                    .sign(algoritmo);
        }catch(JWTCreationException exception){
            throw new RuntimeException("erro ao gerar Token. ", exception);
        }

    }


    public String getSubject(String tokenJWT) {
        try {


            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Bytebank")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        }catch(JWTVerificationException exception){
            throw new RuntimeException("erro ao gerar Token. ", exception);
        }
    }


    private Instant dataExpiracao() {

        return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-03:00"));
    }









}
