package br.com.bytebank.repository;

import br.com.bytebank.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;





public interface ClienteRepository  extends JpaRepository<Cliente, Long> {



}
