package br.com.bytebank.service;

import br.com.bytebank.model.Users;
import br.com.bytebank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private AutenticacaoService service;

    @Test
    void deveDelegarBuscaDeUsuarioPorLogin() {
        Users usuario = new Users(1L, "loginTeste", "senha123");
        when(repository.findByLogin("loginTeste")).thenReturn(usuario);

        assertSame(usuario, service.loadUserByUsername("loginTeste"));
    }
}
