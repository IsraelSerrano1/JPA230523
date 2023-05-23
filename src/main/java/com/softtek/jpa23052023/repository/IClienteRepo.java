package com.softtek.jpa23052023.repository;

import com.softtek.jpa23052023.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepo extends JpaRepository<Cliente, String> {
}
