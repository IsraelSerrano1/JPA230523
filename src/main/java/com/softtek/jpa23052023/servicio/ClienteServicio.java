package com.softtek.jpa23052023.servicio;

import com.softtek.jpa23052023.modelo.Cliente;

import com.softtek.jpa23052023.repository.IClienteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClienteServicio implements IClienteServicio{
    @Autowired
    private IClienteRepo clienteRepo;
    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepo.findAll();
    }
}
