package br.com.bytebank.service;

import br.com.bytebank.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    private TokenService tokenService;

    @BeforeEach
    void setup() throws Exception {
        tokenService = new TokenService();
        setSecret("segredo-teste");
    }

    private void setSecret(String secret) throws Exception {
        Field field = TokenService.class.getDeclaredField("secret");
        field.setAccessible(true);
        field.set(tokenService, secret);
    }

    @Test
    void deveGerarETestarTokenComSucesso() {
        Users usuario = new Users(1L, "usuarioTeste", "senha123");

        String token = tokenService.gerarToken(usuario);

        assertNotNull(token);

        String subject = tokenService.getSubject(token);

        assertEquals("usuarioTeste", subject);
    }

    @Test
    void deveFalharAoValidarTokenComSegredoInvalido() throws Exception {
        Users usuario = new Users(1L, "usuarioTeste", "senha123");
        String token = tokenService.gerarToken(usuario);

        setSecret("segredo-diferente");

        assertThrows(RuntimeException.class, () -> tokenService.getSubject(token));
    }
}
