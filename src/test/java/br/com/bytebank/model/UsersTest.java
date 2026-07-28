package br.com.bytebank.model;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    @Test
    void deveRetornarInformacoesDoUsuarioCorretamente() {
        Users usuario = new Users(1L, "loginTeste", "senhaSegura");

        assertEquals("loginTeste", usuario.getUsername());
        assertEquals("senhaSegura", usuario.getPassword());
        assertTrue(usuario.isAccountNonExpired());
        assertTrue(usuario.isAccountNonLocked());
        assertTrue(usuario.isCredentialsNonExpired());
        assertTrue(usuario.isEnabled());

        Collection<? extends GrantedAuthority> authorities = usuario.getAuthorities();

        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
